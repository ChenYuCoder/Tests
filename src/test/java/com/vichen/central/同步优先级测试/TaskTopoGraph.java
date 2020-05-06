package com.vichen.central.同步优先级测试;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskTopoGraph implements Iterable<TaskNode> {
  private List<TaskNode> starts = new ArrayList<TaskNode>();
  private Map<String, TaskNode> tasks = new HashMap<String, TaskNode>();

  private static final int INDEX_NAME = 0;
  private static final int INDEX_PRE = 1;
  private static final int INDEX_SUC = 2;
  private static final int INDEX_DIR = 3;
  private static final int INDEX_SOD = 4;
  private static final int INDEX_COUNT = 5;

  private static Logger logger = LoggerFactory.getLogger(TaskTopoGraph.class);

  @Override public Iterator<TaskNode> iterator() {

    return new Iterator<TaskNode>() {

      @SuppressWarnings("serial") private Set<TaskNode> currentLevel = new HashSet<TaskNode>() {
        {
          addAll(starts);
        }
      };

      private Iterator<TaskNode> iterator;

      {
        iterator = currentLevel.iterator();
      }

      @Override public boolean hasNext() {
        if (iterator.hasNext()) {
          return true;
        } else {
          Set<TaskNode> nextLevel = new HashSet<TaskNode>();
          for (TaskNode tn : currentLevel) {
            if (tn.getSuccessors() == null) {
              continue;
            }
            nextLevel.addAll(tn.getSuccessors());
          }
          currentLevel = nextLevel;
          iterator = currentLevel.iterator();
          return iterator.hasNext();
        }
      }

      @Override public TaskNode next() {
        return iterator.next();
      }

      @Override public void remove() {
      }
    };
  }


  @Override public int hashCode() {
    return super.hashCode();
  }


  public void addTask(TaskNode tn) {
    tasks.put(tn.getTaskName(), tn);
  }

  public void buildTopoGraphByTemplate(TaskTopoGraph template) {
    Map<String, TaskNode> tempTasks = template.getTasks();
    for (TaskNode tn : tasks.values()) {
      TaskNode tempTn = tempTasks.get(tn.getTaskName());
      tn.setPredecessors(getAvailablePredecessors(tasks, tempTn.getPredecessors()));
      if (tn.getPredecessors() == null) {
        starts.add(tn);
      }
      tn.setSuccessors(getAvailableSuccessors(tasks, tempTn.getSuccessors()));
    }
  }

  private Set<TaskNode> getAvailablePredecessors(Map<String, TaskNode> availableTasks,
    Set<TaskNode> predecessors) {
    if (predecessors == null) {
      logger.info("predecessors is null");
      return null;
    }

    Set<TaskNode> ret = new HashSet<TaskNode>();
    for (TaskNode pre : predecessors) {
      if (availableTasks.containsKey(pre.getTaskName())) {
        ret.add(pre);
      } else {
        Set<TaskNode> availablePres =
          getAvailablePredecessors(availableTasks, pre.getPredecessors());
        if (availablePres == null) {
          continue;
        }
        ret.addAll(availablePres);
      }
    }
    return ret;
  }

  private Set<TaskNode> getAvailableSuccessors(Map<String, TaskNode> availableTasks,
    Set<TaskNode> successors) {
    if (successors == null) {
      logger.info("successors is null");
      return null;
    }

    Set<TaskNode> ret = new HashSet<TaskNode>();
    for (TaskNode suc : successors) {
      if (availableTasks.containsKey(suc.getTaskName())) {
        ret.add(suc);
      } else {
        Set<TaskNode> availableSucs = getAvailableSuccessors(availableTasks, suc.getSuccessors());
        if (availableSucs == null) {
          continue;
        }
        ret.addAll(availableSucs);
      }
    }
    return ret;
  }

  /*
   * Build a task topological graph from a string, each single task is separated by '\n'. Every task
   * has four parts: it's name, predecessors, successors, direction of data and sides of dice. Note:
   * For direction of data, true means data from central to IFE, false means data from IFE to
   * central. Eg: Flight:null:Passenger,Steward:true:1\n Passenger:Flight:Goods,GoodsType:true:1\n
   * Steward:Flight:Goods,GoodsType:true:1\n ...
   */
  public static TaskTopoGraph deserialize(String str) {
    List<TaskNode> starts = new ArrayList<TaskNode>();
    Map<String, TaskNode> tasks = new HashMap<String, TaskNode>();

    String[] items = str.split("\n");
    for (String i : items) {
      String[] parts = i.split(":");
      if (parts.length != INDEX_COUNT) {
        logger.warn("invalid input given : {}", i);
        continue;
      }

      TaskNode tn = new TaskNode();
      tn.setTaskName(parts[INDEX_NAME]);
      if (tasks.containsKey(tn.getTaskName())) {
        logger.warn("duplicate task, taskName:{}", tn.getTaskName());
        continue;
      }
      try {
        tn.setGetTask(Boolean.parseBoolean(parts[INDEX_DIR]));
        tn.setSidesOfDice(Integer.parseInt(parts[INDEX_SOD]));
      } catch (NumberFormatException e) {
        logger.warn("invalid sides of dice given:{}", e.getMessage());
        continue;
      }
      tasks.put(tn.getTaskName(), tn);
    }

    for (String i : items) {
      String[] parts = i.split(":");
      if (parts.length != INDEX_COUNT) {
        continue;
      }

      if (!tasks.containsKey(parts[INDEX_NAME])) {
        continue;
      }

      String[] predecessors = parts[INDEX_PRE].split(",");
      if (predecessors.length == 1 && "null".equals(predecessors[0])) {
        starts.add(tasks.get(parts[INDEX_NAME]));
      } else {
        Set<TaskNode> preTasks = new HashSet<TaskNode>();
        for (String pre : predecessors) {
          if (!tasks.containsKey(pre)) {
            logger.warn("unknown predecessor : {}", pre);
            continue;
          }
          preTasks.add(tasks.get(pre));
        }
        tasks.get(parts[INDEX_NAME]).setPredecessors(preTasks);
      }

      String[] successors = parts[INDEX_SUC].split(",");
      if (successors.length == 1 && "null".equals(successors[0])) {
        continue;
      }
      Set<TaskNode> sucTasks = new HashSet<TaskNode>();
      for (String suc : successors) {
        if (!tasks.containsKey(suc)) {
          logger.warn("unknown successor : {}", suc);
          continue;
        }
        sucTasks.add(tasks.get(suc));
      }
      tasks.get(parts[INDEX_NAME]).setSuccessors(sucTasks);
    }

    TaskTopoGraph graph = new TaskTopoGraph();
    graph.setStarts(starts);
    graph.setTasks(tasks);
    return graph;
  }

  /*
   * @see deserialize
   */
  public static String serialize(TaskTopoGraph graph) {
    Map<String, TaskNode> tasks = graph.getTasks();
    String str = "";
    for (TaskNode tn : tasks.values()) {
      str += tn.getTaskName() + ":";

      Set<TaskNode> predecessors = tn.getPredecessors();
      if (predecessors == null || predecessors.isEmpty()) {
        str += "null";
      } else {
        for (TaskNode pre : predecessors) {
          str += pre.getTaskName() + ",";
        }
      }
      str += ":";

      Set<TaskNode> successors = tn.getSuccessors();
      if (successors == null || successors.isEmpty()) {
        str += "null";
      } else {
        for (TaskNode suc : successors) {
          str += suc.getTaskName() + ",";
        }
      }
      str +=
        ":" + Boolean.toString(tn.isGetTask()) + ":" + Integer.toString(tn.getSidesOfDice()) + "\n";
    }
    return str;
  }

  public List<TaskNode> getStarts() {
    return starts;
  }

  public void setStarts(List<TaskNode> starts) {
    this.starts = starts;
  }

  public Map<String, TaskNode> getTasks() {
    return tasks;
  }

  public void setTasks(Map<String, TaskNode> tasks) {
    this.tasks = tasks;
  }

}
