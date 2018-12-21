package com.longfor.longjian.common.consts;

/**
 * 任务类型的主类型枚举类
 *
 * @author zkm
 * @date 2018/12/12 14:38
 */
public enum CategoryClsTypeEnum {

    /**
     * V2默认检查项
     */
    DEFAULT("V2默认检查项", 0),
    /**
     * 关键工序
     */
    KEY_PROCEDURE("关键工序", 1),
    /**
     * 实测实量
     */
    MEASURE("实测实量", 102),
    /**
     * 移动验房
     */
    HOUSE_QM("移动验房", 201),
    /**
     * 工程管理
     */
    BUILDING_QM("工程管理", 301),

    // V2: 20 + v2
    /**
     * 分部分项
     */
    FBFX("分部分项", 21),
    /**
     * 样板点评
     */
    YB("样板点评", 22),
    /**
     * 日常检查
     */
    RCJC("日常检查", 23),
    /**
     * 月度检查
     */
    YDJC("月度检查", 24),
    /**
     * 季度检查
     */
    JDJC("季度检查", 25),
    /**
     * 入伙验房
     */
    RHYF("入伙验房", 26),
    /**
     * 专项检查
     */
    ZXJC("专项检查", 27),
    /**
     * 分户验收
     */
    FHYS("分户验收", 28);

    private String name;
    private Integer value;

    CategoryClsTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

}
