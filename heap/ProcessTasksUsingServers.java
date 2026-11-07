/*
    You are given two 0-indexed integer arrays servers and tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed to process the j​​​​​​th​​​​ task in seconds.
    
    Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.
    
    At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0). As long as there are free servers and the queue is not empty, the task in the front of the queue will be assigned to a free server with the smallest weight, and in case of a tie, it is assigned to a free server with the smallest index.
    
    If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task. If multiple servers become free at the same time, then multiple tasks from the queue will be assigned in order of insertion following the weight and index priorities above.
    
    A server that is assigned task j at second t will be free again at second t + tasks[j].
    
    Build an array ans​​​​ of length m, where ans[j] is the index of the server the j​​​​​​th task will be assigned to.
    
    Return the array ans​​​​.
    
    Example 1:
    Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
    Output: [2,2,0,2,1,2]
    Explanation: Events in chronological order go as follows:
    - At second 0, task 0 is added and processed using server 2 until second 1.
    - At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
    - At second 2, task 2 is added and processed using server 0 until second 5.
    - At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
    - At second 4, task 4 is added and processed using server 1 until second 5.
    - At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.

    Example 2:
    Input: servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
    Output: [1,4,1,4,1,3,2]
    Explanation: Events in chronological order go as follows: 
    - At second 0, task 0 is added and processed using server 1 until second 2.
    - At second 1, task 1 is added and processed using server 4 until second 2.
    - At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4. 
    - At second 3, task 3 is added and processed using server 4 until second 7.
    - At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9. 
    - At second 5, task 5 is added and processed using server 3 until second 7.
    - At second 6, task 6 is added and processed using server 2 until second 7.
     
    
    Constraints:
    servers.length == n
    tasks.length == m
    1 <= n, m <= 2 * 10^5
    1 <= servers[i], tasks[j] <= 2 * 10^5

    Approach:
    1. We can use two priority queues to keep track of free servers and busy servers
    2. The free servers priority queue will be sorted by weight and index, while the busy servers priority queue will be sortedby finish time, weight, and index.
    3. We will iterate through the tasks and for each task, we will check if there are any free servers.
    If there are, we will assign the task to the free server with the smallest weight and index. If there are no free servers,
    we will wait until a server becomes free and then assign the task to that server.
*/

import java.util.PriorityQueue;

public class ProcessTasksUsingServers {
    class Solution {
        class Pair {
            int wt;
            int idx;

            Pair(int wt, int idx) {
                this.wt = wt;
                this.idx = idx;
            }
        }

        class Tuple {
            long finishTime;
            int wt, idx;

            Tuple(long finishTime, int wt, int idx) {
                this.finishTime = finishTime;
                this.idx = idx;
                this.wt = wt;
            }
        }

        public int[] assignTasks(int[] servers, int[] tasks) {
            int n = servers.length;
            int m = tasks.length;

            // Free Servers (wt, idx)
            PriorityQueue<Pair> freeServers = new PriorityQueue<>(
                    (a, b) -> a.wt != b.wt ? Integer.compare(a.wt, b.wt) : Integer.compare(a.idx, b.idx));

            // Busy Servers (finishTime, wt, idx)
            PriorityQueue<Tuple> busyServers = new PriorityQueue<>(
                    (a, b) -> a.finishTime != b.finishTime ? Long.compare(a.finishTime, b.finishTime)
                            : a.wt != b.wt ? Integer.compare(a.wt, b.wt) : Integer.compare(a.idx, b.idx));

            for (int i = 0; i < n; i++) {
                freeServers.add(new Pair(servers[i], i));
            }

            int[] res = new int[m];

            for (int i = 0; i < m; i++) {
                while (!busyServers.isEmpty() && busyServers.peek().finishTime <= i) {
                    Tuple curr = busyServers.poll();
                    freeServers.add(new Pair(curr.wt, curr.idx));
                }

                if (freeServers.isEmpty()) {
                    Tuple curr = busyServers.poll();
                    res[i] = curr.idx;
                    busyServers.add(new Tuple(tasks[i] + curr.finishTime, curr.wt, curr.idx));
                } else {
                    Pair curr = freeServers.poll();
                    res[i] = curr.idx;
                    busyServers.add(new Tuple(tasks[i] + i, curr.wt, curr.idx));
                }
            }

            return res;
        }
    }
}
