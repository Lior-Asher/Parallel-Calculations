# Parallel-Calculations  
Calculating a number of math expressions in parallel.  
The application uses a predefined number of threads to calculate a number of math problems in parallel.  
A thread pool size is defined by the user, then the pool assigns tasks to the threads to complete.  
When a thread has completed it's task, it reports it and returns to the thread pool. If there are other jobs waiting, avaiable therads will be assigned to them.
