package com.longfor.longjian.common.consts;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;

public enum HouseQmCheckTaskIssueCondition {
	Slight    (1, "轻微 "),
	RangePoor (2, "较差"),
	Serious   (3, "严重");
	
	@Getter
	@Setter
	private Integer value;

	@Getter
	@Setter
	private String label;

	HouseQmCheckTaskIssueCondition(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, HouseQmCheckTaskIssueCondition> conditionMap = Maps.newHashMap();

    static {
        for (HouseQmCheckTaskIssueCondition status : HouseQmCheckTaskIssueCondition.values()) {
        	conditionMap.put(status.getValue(), status);
        }
    }
	
	public static String getLabel(Integer value) {
		HouseQmCheckTaskIssueCondition condition=conditionMap.get(value);
		if(condition==null) {
			return "";
		}
		return condition.getLabel();
		
	}
}
