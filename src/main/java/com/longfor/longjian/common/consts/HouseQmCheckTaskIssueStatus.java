package com.longfor.longjian.common.consts;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;

public enum HouseQmCheckTaskIssueStatus {
	NoProblem(10, "无问题"), NoteNoAssign(20, "待分配"), AssignNoReform(30, "待整改"), ReformNoCheck(50, "待销项"),
	CheckYes(60, "已销项"), Cancel(70, "已取消");
	@Getter
	@Setter
	private Integer value;

	@Getter
	@Setter
	private String label;

	HouseQmCheckTaskIssueStatus(Integer value, String label) {
		this.value = value;
		this.label = label;
	}
	
	private static final Map<Integer, HouseQmCheckTaskIssueStatus> statusMap = Maps.newHashMap();

    static {
        for (HouseQmCheckTaskIssueStatus status : HouseQmCheckTaskIssueStatus.values()) {
        	statusMap.put(status.getValue(), status);
        }
    }
	
	public static String getLabel(Integer value) {
		HouseQmCheckTaskIssueStatus status=statusMap.get(value);
		if(status==null) {
			return "";
		}
		return status.getLabel();
		
	}
}
