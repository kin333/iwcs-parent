package com.wisdom.service.glusterfs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.wisdom.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

@Service
public class FileSystemService {

    @Autowired
    private ApplicationProperties properties;

    private String accessSubPath;
    private String accessKeyID;
    private String secretKey;
    private String bucketName;
    private String endpoint;

    @PostConstruct
    public void init() {
        this.accessSubPath = properties.getGlusterfs().getAccessSubPath();
        this.accessKeyID = properties.getGlusterfs().getAccessKeyID();
        this.secretKey = properties.getGlusterfs().getSecretKey();
        this.bucketName = properties.getGlusterfs().getBucketName();
        this.endpoint = properties.getGlusterfs().getEndpoint();
    }

    public String uploadFileToBucket(InputStream inputStream, String keyName, Long fileLength, String uploadBucketName) {
        AWSCredentials credentials;
        AmazonS3 s3client;
        credentials = new BasicAWSCredentials(this.accessKeyID, this.secretKey);
//        s3client = AmazonS3ClientBuilder.standard()
//                .build();
        s3client = new AmazonS3Client(credentials);
//        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        s3client.setEndpoint(this.endpoint);
        s3client.setS3ClientOptions(
                S3ClientOptions.builder()
                        .setPathStyleAccess(true)
                        .disableChunkedEncoding().build());
        ObjectMetadata metadata = new ObjectMetadata();
        try {
            System.out.println("Uploading a new object to S3 from a file\n");

            metadata.setContentMD5(null);
            metadata.setContentLength(fileLength);

            s3client.putObject(new PutObjectRequest(
                    this.bucketName + uploadBucketName, keyName, inputStream, metadata));

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            ase.getRawResponseContent();
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
        return endpoint + "/" + accessSubPath + "/" + this.bucketName + uploadBucketName + "/" + keyName;
    }


    public String uploadFile(InputStream inputStream, String keyName, Long fileLength) {

        return uploadFileToBucket(inputStream, keyName, fileLength, "");
    }

    public void uploadFile(String uploadFileName, String keyName) throws Exception {

        AWSCredentials credentials = new BasicAWSCredentials(this.accessKeyID, this.secretKey);

        TransferManager tm = new TransferManager(credentials);
        tm.getAmazonS3Client().setEndpoint(this.endpoint);
        tm.getAmazonS3Client().setS3ClientOptions(
                S3ClientOptions.builder()
                        .setPathStyleAccess(true)
                        .disableChunkedEncoding().build());
        System.out.println("Hello");
        File file = new File(uploadFileName);

        FileInputStream is = new FileInputStream(file);
        // TransferManager processes all transfers asynchronously,
        // so this call will return immediately.
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentMD5(null);
        metadata.setContentLength(file.length());
        Upload upload = tm.upload(
                bucketName, keyName, is, metadata);
        System.out.println("Hello2");

        try {
            // Or you can block and wait for the upload to finish
            upload.waitForCompletion();
            System.out.println("UploadFile complete.");
        } catch (AmazonClientException amazonClientException) {
            System.out.println("Unable to upload file, upload was aborted.");
            amazonClientException.printStackTrace();
        }
    }

    /**
     * MD5文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String md5file(File file) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024 * 100];
        int p = 0;
        while ((p = in.read(buf)) != -1) {
            messageDigest.update(buf, 0, p);
        }
        in.close();
        byte[] digest = messageDigest.digest();

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(digest);
    }

}
