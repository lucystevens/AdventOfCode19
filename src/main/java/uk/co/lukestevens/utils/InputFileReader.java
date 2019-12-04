package uk.co.lukestevens.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFileReader {
	
	private String path;

	/**
	 * Constructs a new file reader with a given file
	 * @param filename The filename within the inputs folder
	 */
	public InputFileReader(String filename) {
		this.path = "/inputs/" + filename;
	}
	
	Stream<String> readFileAsStream() throws IOException {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						InputFileReader.class.getResourceAsStream(this.path)))) {
			
			return reader.lines().collect(Collectors.toList()).stream();
		} catch(UncheckedIOException io) {
			throw io.getCause();
		}
	}
	
	public List<Integer> readFileAsListOfIntegers() throws IOException {
		return this.readFileAsStream().map(Integer::parseInt).collect(Collectors.toList());
	}
	
	public List<String> readFileAsListOfStrings() throws IOException {
		return this.readFileAsStream().collect(Collectors.toList());
	}
	
	public int[] readFileAsArrayOfIntegers() throws IOException {
		String line = this.readFileAsStream().findFirst().get();
		String[] split = line.split(",");
		int[] result = new int[split.length];
		for(int i = 0; i<split.length; i++) {
			result[i] = Integer.parseInt(split[i]);
		}
		return result;
	}

}
