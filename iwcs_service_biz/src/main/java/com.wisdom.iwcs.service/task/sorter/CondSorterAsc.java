package com.wisdom.iwcs.service.task.sorter;

import com.wisdom.iwcs.service.task.conditions.ConditionBase;

import java.util.Comparator;

/**
 * @author  Tony Wang
 * @date 2018/8/8
 */
public class CondSorterAsc implements Comparator<ConditionBase> {

	@Override
	public int compare(ConditionBase o1, ConditionBase o2) {
		// TODO Auto-generated method stub
		return o1.getOrder() - o2.getOrder();
	}

}
