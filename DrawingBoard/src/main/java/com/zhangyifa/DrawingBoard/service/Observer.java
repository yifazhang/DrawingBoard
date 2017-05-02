package com.zhangyifa.DrawingBoard.service;

//观察者接口，每一个观察者都必须实现这个接口
public interface Observer {
	//这个方法是观察者在观察对象产生变化时所做的响应动作，从中传入了观察的对象和一个预留参数
    void update(Object o, Object arg);
}
