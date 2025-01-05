package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group2;

public class ObjectNode implements Node{
      Node node;
      public void parse(Context context){
            node =new ObjectPronounOrNounNode();
            node.parse(context);
     }
      public void execute(){
           node.execute();
     }
}