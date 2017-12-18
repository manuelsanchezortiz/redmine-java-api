package com.taskadapter.redmineapi.bean;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TimeEntry implements Identifiable {

    private final PropertyStorage storage;

    /**
     * database numeric Id
     */
    public final static Property<Integer> DATABASE_ID = new Property<>(Integer.class, "id");

    /**
     * database Id of the Issue
     */
    public final static Property<Integer> ISSUE_ID = new Property<>(Integer.class, "issueId");

    /**
     * database Id of the project
     */
    public final static Property<Integer> PROJECT_ID = new Property<>(Integer.class, "projectId");

    public final static Property<String> PROJECT_NAME = new Property<>(String.class, "projectName");
    public final static Property<String> USER_NAME = new Property<>(String.class, "userName");
    public final static Property<Integer> USER_ID = new Property<>(Integer.class, "userId");
    public final static Property<String> ACTIVITY_NAME = new Property<>(String.class, "activityName");
    public final static Property<Integer> ACTIVITY_ID = new Property<>(Integer.class, "activityId");
    public final static Property<Float> HOURS = new Property<>(Float.class, "hours");
    public final static Property<String> COMMENT = new Property<>(String.class, "comment");
    public final static Property<Date> SPENT_ON = new Property<>(Date.class, "spentOn");
    public final static Property<Date> CREATED_ON = new Property<>(Date.class, "createdOn");
    public final static Property<Date> UPDATED_ON = new Property<>(Date.class, "updatedOn");
	public static final Property<Set<CustomField>> CUSTOM_FIELDS = new Property(Set.class, "custom_fields");

    /**
     * @param id database Id
     */
    TimeEntry(Integer id) {
        storage = new PropertyStorage();
        storage.set(DATABASE_ID, id);
        storage.set(CUSTOM_FIELDS, new HashSet<>());
    }

    public Integer getId() {
        return storage.get(DATABASE_ID);
    }

    public Integer getUserId() {
        return storage.get(USER_ID);
    }

    public void setUserId(Integer userId) {
        storage.set(USER_ID, userId);
    }

    public Integer getIssueId() {
        return storage.get(ISSUE_ID);
    }

    public void setIssueId(Integer issueId) {
        storage.set(ISSUE_ID, issueId);
    }

    public Integer getProjectId() {
        return storage.get(PROJECT_ID);
    }

    public void setProjectId(Integer projectId) {
        storage.set(PROJECT_ID, projectId);
    }

    public String getProjectName() {
        return storage.get(PROJECT_NAME);
    }

    public void setProjectName(String projectName) {
        storage.set(PROJECT_NAME, projectName);
    }

    public String getActivityName() {
        return storage.get(ACTIVITY_NAME);
    }

    public void setActivityName(String activityName) {
        storage.set(ACTIVITY_NAME, activityName);
    }

    public Integer getActivityId() {
        return storage.get(ACTIVITY_ID);
    }

    public void setActivityId(Integer activityId) {
        storage.set(ACTIVITY_ID, activityId);
    }

    public Float getHours() {
        return storage.get(HOURS);
    }

    public void setHours(Float hours) {
        storage.set(HOURS, hours);
    }

    public String getComment() {
        return storage.get(COMMENT);
    }

    public void setComment(String comment) {
        storage.set(COMMENT, comment);
    }

    public Date getSpentOn() {
        return storage.get(SPENT_ON);
    }

    public void setSpentOn(Date spentOn) {
        storage.set(SPENT_ON, spentOn);
    }

    public Date getCreatedOn() {
        return storage.get(CREATED_ON);
    }

    public void setCreatedOn(Date createdOn) {
        storage.set(CREATED_ON, createdOn);
    }

    public Date getUpdatedOn() {
        return storage.get(UPDATED_ON);
    }

    public void setUpdatedOn(Date updatedOn) {
        storage.set(UPDATED_ON, updatedOn);
    }

    public String getUserName() {
        return storage.get(USER_NAME);
    }

    public void setUserName(String userName) {
        storage.set(USER_NAME, userName);
    }

    /**
     * @return the custom field with given name or NULL if the field is not found
     */
    public CustomField getCustomField(String name) {
    	return storage.get(CUSTOM_FIELDS).stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "User \"" + getUserName() + "\" spent " + getHours()
                + " hours on task " + getIssueId() + " (project \"" + getProjectName()
                + "\") doing " + getActivityName();
    }
    
    public Set<CustomField> getCustomFields() {
    		return storage.get(CUSTOM_FIELDS);
    }

    public void clearCustomFields() {
        storage.set(CUSTOM_FIELDS, new HashSet<>());
    }

    public void addCustomFields(Collection<CustomField> customFields) {
    		storage.get(CUSTOM_FIELDS).addAll(customFields);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeEntry timeEntry = (TimeEntry) o;

        if (getId() != null ? !getId().equals(timeEntry.getId()) : timeEntry.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    public boolean isValid() {
        return (getHours() != null) && (getProjectId() != null || getIssueId() != null);
    }

    public PropertyStorage getStorage() {
        return storage;
    }
}
