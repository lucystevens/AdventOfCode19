package uk.co.lukestevens.challenges;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordCracker {
	
	public boolean isValidPassword(String password) {
		// 6 digits
		// Ignoring this test as will always be true
		//if(!password.matches("\\d{6}")) {
		//	return false;
		//}
		
		// contains 2 adjacent of the same digit
		if(!password.matches("\\d*(\\d)\\1\\d*")) {
			return false;
		}
		
		// Check each number is bigger than the last
		int lastChar = 0;
		for(int c : password.toCharArray()) {
			if(c < lastChar) {
				return false;
			}
			lastChar = c;
		}
		
		return true;
	}
	
	public boolean isValidPasswordExt(String password) {
		int lastChar = 0;
		boolean foundAdj = false;
		int adjacentCharCount = 0;
		for(int c : password.toCharArray()) {
			
			if(c < lastChar) {
				return false;
			}
			
			if(c == lastChar) {
				adjacentCharCount++;
			}
			else {
				if(adjacentCharCount == 2) {
					foundAdj = true;
				}
				adjacentCharCount = 1;
			}
			
			lastChar = c;
		}
		
		return foundAdj || adjacentCharCount == 2;
	}
	
	public List<String> getPasswordsInRange(int rangeStart, int rangeEnd){
		return IntStream.range(rangeStart, rangeEnd)
				.mapToObj(String::valueOf)
				.filter(this::isValidPassword)
				.collect(Collectors.toList());
	}
	
	public List<String> getPasswordsInRangeExt(int rangeStart, int rangeEnd){
		return IntStream.range(rangeStart, rangeEnd)
				.mapToObj(String::valueOf)
				.filter(this::isValidPasswordExt)
				.collect(Collectors.toList());
	}

}
