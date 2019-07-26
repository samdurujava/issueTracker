package com.sam.springsecurity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectID;

    private String projectName;

    private String projectDescription;

    private String startDate;

    private String leadDeveloper;

    private ArrayList<Issue> issue;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Issue> issues;


    public long getProjectID() {
        return projectID;
    }

    public void setProjectID(long projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLeadDeveloper() {
        return leadDeveloper;
    }

    public void setLeadDeveloper(String leadDeveloper) {
        this.leadDeveloper = leadDeveloper;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Project() {

    }
}