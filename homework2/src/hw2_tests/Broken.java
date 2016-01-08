/**
 * @author Joel May
 * Copyright 2015
 */

package hw2_tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;

import util.PermutationGenerator;
import static org.junit.Assert.*;

public class Broken {

	public static void main(String[] args) {
		Random rand = new Random(1337);
		PermutationGenerator gen = new PermutationGenerator(rand);
		System.out.println(Arrays.toString(gen.generate(5)));
	}
}
