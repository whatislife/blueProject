package com.zhht.jna;
import java.util.Objects;

public class Featdata {

	
	public float outputSim(String featdataStrA, String featdataStrB) {
		
		if (null == featdataStrA || null == featdataStrB) {
			return -1;
		}
		float calSimilarity = Similarity.INSTANCE.CalSimilarity(featdataStrA, featdataStrB);
		if (Objects.equals(calSimilarity, -1)) {
			System.out.println("获取特征相似度失败:" + calSimilarity);
			return calSimilarity;
		}
		return calSimilarity;
	}

}
