package com.zhht.jna;

public class FrankManBlue {
	
	public static void main(String[] args) {
		String featdataStrA = args[0];
		String featdataStrB = args[1];
		System.out.println("featdataStrA:"+featdataStrA);
		System.out.println("featdataStrB:"+featdataStrB);
		float f = new Featdata().outputSim(featdataStrA, featdataStrB);
		System.out.println("============:"+f);
	}

}
