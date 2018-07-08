/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zad2;

/**
 *
 * @author Lukasz Piwowarski s18226
 */
class StringTask implements Runnable
{
    private volatile TaskState currentTaskState; 
    private final String inputData;
    private String outputData;
    private final int count;
    private volatile boolean abort = false;
    
    StringTask(String inputData, int count)
    {    
        if (inputData == null)
            throw new NullPointerException("inputData");
        
        this.inputData = inputData;
        this.count = count;
        currentTaskState = TaskState.CREATED;
    }

    public void abort()
    {
        if (getState() != TaskState.RUNNING)
            throw new IllegalStateException("Cannot abort not started task!");

        abort = true;
    }
    public TaskState getState()
    {     
      //  synchronized (this)
//        {
            return currentTaskState;
  //      }
    }
    private void setState(TaskState newState)
    {
       // synchronized (this) // probably not needede because of volatile
        //{
            currentTaskState = newState;
//        }
    }
    public void start()
    {
        if (getState() == TaskState.RUNNING)
            throw new IllegalStateException("Cannot start already started task!");        
        setState(TaskState.RUNNING);        
        outputData = "";
        Thread t = new Thread(this);            
        t.start();                   
    }
    public boolean isDone()
    {
        return getState() == TaskState.READY || getState() == TaskState.ABORTED;
    }
    public String getResult()
    {
        return outputData;
    }
    @Override
    public void run()
    {
        String curData = inputData;
        for (int i = 0; i < count - 1 && !abort ; i++)
        {
            curData+=inputData;
        }
        outputData = curData;       
        setState(abort ? TaskState.ABORTED : TaskState.READY);
        abort = false;
    }
    
}
