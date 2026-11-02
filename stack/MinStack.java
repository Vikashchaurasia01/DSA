import java.util.Stack;

/*
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    Implement the MinStack class:

    MinStack() initializes the stack object.
    void push(int value) pushes the element value onto the stack.
    void pop() removes the element on the top of the stack.
    int top() gets the top element of the stack.
    int getMin() retrieves the minimum element in the stack.
    You must implement a solution with O(1) time complexity for each function.

    Example 1:
    Input
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]

    Output
    [null,null,null,null,-3,null,0,-2]

    Explanation
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin(); // return -3
    minStack.pop();
    minStack.top();    // return 0
    minStack.getMin(); // return -2
    

    Constraints:

    -2^31 <= val <= 2^31 - 1
    Methods pop, top and getMin operations will always be called on non-empty stacks.
    At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
*/

public class MinStack {

    //Time: O(1) for all operations (push, pop, top, getMin)
    //Space: O(2 * n) for the stack, where n is the number of elements pushed onto the stack and store the minimum value along with each element
    class Pair {
        int val;
        int min;

        Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    Stack<Pair> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int value) {
        if (stack.isEmpty()) {
            stack.push(new Pair(value, value));
        } else {
            if (value < stack.peek().min) {
                stack.push(new Pair(value, value));
            } else {
                stack.push(new Pair(value, stack.peek().min));
            }
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        }

    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }



    // Time: O(1) for all operations (push, pop, top, getMin)
    // Space: O(n) for the stack, where n is the number of elements pushed onto the stack

    // class Solution {
    //     int min = Integer.MAX_VALUE;
    //     Stack<Integer> stack = new Stack<>();

    //     public MinStack() {
            
    //     }

    //     public void push(int value) {
    //         if (value <= min) {
    //             stack.push(min);
    //             min = value;
    //         }
    //         stack.push(value);
    //     }

    //     public void pop() {
    //         if (stack.pop() == min)
    //             min = stack.pop();
    //     }

    //     public int top() {
    //         return stack.peek();
    //     }

    //     public int getMin() {
    //         return min;
    //     }
    // }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
