package com.wzl.j8new.datastructure.stack;

import java.util.Scanner;

/**
 * @author wangzhilong
 * @date 2020/8/5 16:05
 * @Description: 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出程序");
            System.out.println("push:表示入栈");
            System.out.println("pop:表示出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value  = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.printf("出栈的数据是%d\n",arrayStack.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }

    static class ArrayStack{
        private int maxSize;//栈大小
        private int[] stack;//数组模拟栈，元素就放在数组中
        private int top = -1;//栈顶，初始化-1
        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }
        //栈满
        public boolean isFull(){
            return top == maxSize - 1;
        }
        //栈空
        public boolean isEmpty(){
            return top == -1;
        }

        //入栈
        public void push(int value) {
            if (isFull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }
        //出栈
        public int pop(){
            if (isEmpty()) {
                throw new RuntimeException("栈空");
            }
            int value = stack[top];
            top--;
            return value;
        }
        //遍历
        public void list() {
            if (isEmpty()) {
                System.out.println("栈空");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%d\n",i,stack[i]);
            }
        }
    }
}
