package com.uptoser.java.design_patterns.behavioral_patterns.observer.group2;

/**
 * 拉数据方式是指具体主题不将变化后的数据交给具体观察者，而是提供了获得这些数据的方法，
 * 具体观察者在得到通知后，可以调用具体主题提供的方法得到数据（观察者自己把数据“拉"过来），但需要自己判断数据是否发生了变化。
 * 当具体主题不知道具体观察者是否需要这些变换后的数据时往往采用拉数据的方式。
 *
 * 假如一个人只关心价格，另一个只关心等级
 */
public class Application {

	public static void main(String[] args) {
		// 手机店
		PhoneShop phoneShop = new PhoneShop();
		phoneShop.setPhoneName("Iphone8");
		phoneShop.setPrice(8999);
		phoneShop.setLevel(5);
		// 电脑店
		ComputerShop computerShop = new ComputerShop();
		computerShop.setComputerName("Dell");
		computerShop.setPrice(4999);
		computerShop.setLevel(4);
		// 客户在手机店和电脑进行登记
		CustomerA a = new CustomerA(phoneShop, computerShop, "小A");
		new CustomerB(phoneShop, computerShop, "小B");
		// 通知消息
		computerShop.notifyObservers();
		phoneShop.getMsg();
		computerShop.setPrice(4499);
		computerShop.getMsg();
		phoneShop.getMsg();

	}

}
