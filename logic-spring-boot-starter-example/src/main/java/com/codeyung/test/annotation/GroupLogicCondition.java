package com.codeyung.test.annotation;

import java.util.Objects;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-04-12.00:40
 */
public class GroupLogicCondition {

    public static final String LOGIC_GROUP = "default";

    private String group;


    public GroupLogicCondition() {
    }

    public GroupLogicCondition(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static GroupLogicCondition newDefault() {
        return new GroupLogicCondition(LOGIC_GROUP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupLogicCondition that = (GroupLogicCondition) o;

        if (!Objects.equals(group, that.group)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(group);
    }

    @Override
    public String toString() {
        return "LogicCondition{" +
                "group='" + group + '\'' +
                '}';
    }

}
