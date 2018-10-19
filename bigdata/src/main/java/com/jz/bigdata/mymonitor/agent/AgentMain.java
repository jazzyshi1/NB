package com.jz.bigdata.mymonitor.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * Created by jazzyshi on 2018/10/17.
 */
public class AgentMain implements ClassFileTransformer{

    

    public static void main(String[] args){
        System.out.println("启动main");
    }

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("启动premain");
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        return new byte[0];
    }
}
