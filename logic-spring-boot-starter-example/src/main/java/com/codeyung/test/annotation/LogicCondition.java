package com.codeyung.test.annotation;

import java.util.Objects;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.00:40
 */
public class LogicCondition {

    public static final String LOGIC_GROUP = "default";
    public static final int LOGIC_TYPE = 0;

    private String group;
    private int type;


    public LogicCondition() {
    }

    public LogicCondition(String group, int type) {
        this.group = group;
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static LogicCondition newDefault() {
        return new LogicCondition(LOGIC_GROUP, LOGIC_TYPE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogicCondition that = (LogicCondition) o;

        if (!Objects.equals(group, that.group)) {
            return false;
        }

        if (type != that.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, type);
    }

    @Override
    public String toString() {
        return "LogicCondition{" +
                "group='" + group + '\'' +
                ", type=" + type +
                '}';
    }


}
