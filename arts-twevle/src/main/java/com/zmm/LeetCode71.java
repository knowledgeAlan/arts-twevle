package com.zmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zhongzuoming <zhongzuoming, 1299076979@qq.com>
 * @version v1.0
 * @Description baipao
 * @encoding UTF-8
 * @date 2019/12/4
 * @time 19:21
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public class LeetCode71 {
    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
    }

    public static String simplifyPath(String path) {

        Stack<String> stack = new Stack();
        String[]  strings = path.split("/");
        for (String s : strings){
            if (!stack.isEmpty()&&s.equals("..")){
                stack.pop();
            }else if(!s.equals("")&&!s.equals(".")&&!s.equals("..")){
                stack.push(s);
            }

        }
        List<String> arrayList = new ArrayList(stack);
        return "/"+String.join("/",arrayList);
    }
}
