package uk.co.lukestevens.challenges.spaceimage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import uk.co.lukestevens.utils.Grid;
import uk.co.lukestevens.utils.ListWithCursor;

public class SpaceImage {
	
	private final int width;
	private final int height;
	private final List<Grid<Integer>> image = new ArrayList<>();
	
	public SpaceImage(int width, int height, List<Integer> format) {
		this.width = width;
		this.height = height;
		this.parse(format);
	}

	void parse(List<Integer> input) {
		ListWithCursor<Integer> format = new ListWithCursor<>(input);
		int layers = input.size()/(width*height);
		
		for(int layerCursor = 0; layerCursor<layers; layerCursor++) {
			Grid<Integer> layer = new Grid<>();
			for(int rowCursor = 0; rowCursor<height; rowCursor++) {
				for(int columnCursor = 0; columnCursor<width; columnCursor++) {
					layer.set(columnCursor, rowCursor, format.next());
				}
			}
			this.image.add(layer);
		}
	}

	public Grid<Integer> getLayerWithFewestZeroes(){
		int layerIndex = 0;
		long minZeroes = Long.MAX_VALUE;
		for(int i = 0; i<image.size(); i++) {
			long zeroes = this.getDigitCountOnLayer(image.get(i), 0);
			if(zeroes < minZeroes) {
				layerIndex = i;
				minZeroes = zeroes;
			}
		}
		return image.get(layerIndex);
	}
	
	public long getDigitCountOnLayer(Grid<Integer> layer, int digit) {
		return layer.stream().map(Entry::getValue).filter(v -> v == digit).count();
	}
	
	public Grid<Integer> getDecodedImage() {
		Grid<Integer> finalImage = new Grid<>();
		for(int x = 0; x<width; x++) {
			for(int y = 0; y<height; y++) {
				int colour = 2;
				for(int i = 0; i<image.size() && colour == 2; i++) {
					Grid<Integer> layer = image.get(i);
					colour = layer.get(x, y);
				}
				finalImage.set(x, y, colour);
			}
		}
		return finalImage;
	}

}
