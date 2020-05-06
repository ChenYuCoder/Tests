package com.vichen.central.同步优先级测试;

import java.util.Set;

public class TaskNode {
  private Set<TaskNode> predecessors;
  private Set<TaskNode> successors;
  private String taskName;
  private int sidesOfDice;
  private boolean isGetTask;

  @Override public boolean equals(Object other) {
    return (other instanceof TaskNode) && taskName.equals(((TaskNode) other).taskName);
  }

  @Override public int hashCode() {
    return super.hashCode();
  }

  public Set<TaskNode> getPredecessors() {
    return predecessors;
  }

  public void setPredecessors(Set<TaskNode> predecessors) {
    this.predecessors = predecessors;
  }

  public Set<TaskNode> getSuccessors() {
    return successors;
  }

  public void setSuccessors(Set<TaskNode> successors) {
    this.successors = successors;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public int getSidesOfDice() {
    return sidesOfDice;
  }

  public void setSidesOfDice(int sidesOfDice) {
    this.sidesOfDice = sidesOfDice;
  }

  public boolean isGetTask() {
    return isGetTask;
  }

  public void setGetTask(boolean isGetTask) {
    this.isGetTask = isGetTask;
  }

}
