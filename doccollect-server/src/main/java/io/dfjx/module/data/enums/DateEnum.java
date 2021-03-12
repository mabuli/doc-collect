package io.dfjx.module.data.enums;

import lombok.Data;

/**
 * @Title:io.dfjx.module.data.enums
 * @Description:
 * @Author: 陈松
 * @Date: 2021/3/7 11:28
 * @Version: 1.0
 */
public enum DateEnum {
	TODAY("TODAY", "当天"),WEEK("WEEK","近7天")
	, MONTH("MONTH", "近30天"), HALF_YEAR("HALF_YEAR", "近半年"), ALL("ALL", "所有");

	private String name;

	private String annotation;

	DateEnum(String type, String annotation) {
		this.name = type;
		this.annotation = annotation;
	}

	public String getName() {
		return name;
	}

	public String getAnnotation() {
		return annotation;
	}}
