package com.learning.java.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MultipleDataTypes implements Serializable {

	private String id;
	private boolean booleanTest;
	private long longTest;
	private int intTest;
	private ArrayList<Integer> arrayIntegerTest;
	private Map<String, Object> mapTest;
	private double doubleTest;

}
