package com.wisdom.test;

import com.wisdom.iwcs.common.utils.XmlToBeanUtils;
import com.wisdom.iwcs.domain.hikSync.MapConfigDto;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/21 17:17
 */
public class TestXmlToBean {

    public static void main(String[] args) throws Exception {

        xmlToJavaBean();

    }


    public static void xmlToJavaBean() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><MapCfg><MapName>ZCWL</MapName><MapType>2</MapType><MapQRCode>XY</MapQRCode><Row>500</Row><Col>500</Col><PointInfo><id>0</id><xpos>50.000</xpos><ypos>101.860</ypos><value areaCode=\"1\">1</value><RoadProperty>0</RoadProperty><Rot>0</Rot><alldirRot>1</alldirRot><Dir>1</Dir><allDir>9</allDir><TranZoneType ParkType=\"-1\">0</TranZoneType><NeighbInfo><id>78</id><distance>1.400</distance><Rever>0</Rever><Speed>0.0</Speed><Ultrasonic>0</Ultrasonic><LeftWidth>0</LeftWidth><RightWidth>0</RightWidth><robottype>-1</robottype><PodDir>999</PodDir><StartDir>666.0</StartDir><StopDir>666.0</StopDir><AlarmVoice>0</AlarmVoice><id>3</id><distance>1.400</distance><Rever>1</Rever><Speed>0.0</Speed><Ultrasonic>0</Ultrasonic><LeftWidth>0</LeftWidth><RightWidth>0</RightWidth><robottype>-1</robottype><PodDir>999</PodDir><StartDir>666.0</StartDir><StopDir>666.0</StopDir><AlarmVoice>0</AlarmVoice><id>1</id><distance>1.400</distance><Rever>1</Rever><Speed>0.0</Speed><Ultrasonic>0</Ultrasonic><LeftWidth>0</LeftWidth><RightWidth>0</RightWidth><robottype>-1</robottype><PodDir>999</PodDir><StartDir>666.0</StartDir><StopDir>666.0</StopDir><AlarmVoice>0</AlarmVoice></NeighbInfo></PointInfo><PodPosTrust>0</PodPosTrust><ActUnderPod>0</ActUnderPod><XOffset>0</XOffset><YOffset>0</YOffset></MapCfg>";
        MapConfigDto mapContentDto = XmlToBeanUtils.xmlToBean(xml, MapConfigDto.class);
        System.out.println(mapContentDto.toString());
    }
}

