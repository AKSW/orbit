package org.aksw.orbit.benchmark.measure;

import java.util.ArrayList;
import java.util.List;

public class MAP extends PrecisionAt {

	public MAP(int k) {
		super(k);
	}

	@Override
	public double evaluate(List<String> benchmarkAnswerList, List<String> systemAnswerList, int k) {
		double map = 0;
		int benchMinK = Math.min(benchmarkAnswerList.size(), k);
		int systemMinK = Math.min(systemAnswerList.size(), k);
		List<String> benchmarkAnswerSubList = new ArrayList<String>(benchmarkAnswerList.subList(0, benchMinK));
		List<String> systemAnswerSubList = new ArrayList<String>(systemAnswerList.subList(0, systemMinK));
		for(int i = 0; i < systemMinK; i++) {
			String answerAtI = null;
			answerAtI = systemAnswerSubList.get(i);
			if(benchmarkAnswerSubList.contains(answerAtI)) {
				map += super.evaluate(benchmarkAnswerSubList, systemAnswerSubList, i+1);
			}
		}
		systemAnswerSubList.retainAll(benchmarkAnswerSubList);
		int intersectionSize = systemAnswerSubList.size();
		if(intersectionSize == 0) {
			return 0;
		}
		return map / intersectionSize;
	}
}
