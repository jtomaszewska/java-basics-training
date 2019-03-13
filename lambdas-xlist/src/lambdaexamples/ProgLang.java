package lambdaexamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgLang {

	private Map<String, ArrayList<String>> langsMap = new LinkedHashMap<>();
	private List<String[]> progLang = new ArrayList<>();
	private Map<String, ArrayList<String>> progsMap = new LinkedHashMap<>();

	public ProgLang(String nazwaPliku) {
		Path path = Paths.get(nazwaPliku);
		try {
			for (String line : Files.readAllLines(path)) {
				progLang.add(line.split("\\t"));
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		createLangsMap(progLang);
		createProgsMap(progLang);
	}

	private Map<String, ArrayList<String>> sorted(Map<String, ArrayList<String>> mapToSort,
			Comparator<? super Entry<String, ArrayList<String>>> comparator) {

		List<Entry<String, ArrayList<String>>> sortedList = mapToSort.entrySet().stream().sorted(comparator)
				.collect(Collectors.toList());
		LinkedHashMap<String, ArrayList<String>> sortedMap = listToLinkedHashMap(sortedList);
		return sortedMap;
	}

	private Map<String, ArrayList<String>> filtered(Map<String, ArrayList<String>> mapToSort,
			Predicate<? super Entry<String, ArrayList<String>>> predicate) {
		List<Entry<String, ArrayList<String>>> filteredList = mapToSort.entrySet().stream().filter(predicate)
				.collect(Collectors.toList());
		LinkedHashMap<String, ArrayList<String>> filteredMap = listToLinkedHashMap(filteredList);
		return filteredMap;
	}

	private LinkedHashMap<String, ArrayList<String>> listToLinkedHashMap(List<Entry<String, ArrayList<String>>> list) {
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<>();
		for (Entry<String, ArrayList<String>> entry : list) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	private void createProgsMap(List<String[]> progLang) {
		ArrayList<String> names = new ArrayList<>();
		for (String[] line : progLang) {
			for (int i = 1; i < line.length; i++) {
				names.add(line[i]);
			}
		}

		for (String name : names) {
			ArrayList<String> knownLangs = new ArrayList<>();
			for (Map.Entry<String, ArrayList<String>> keyValuePair : langsMap.entrySet()) {
				String lang = keyValuePair.getKey();
				ArrayList<String> programmers = keyValuePair.getValue();
				if (programmers.contains(name)) {
					knownLangs.add(lang);
				}
			}
			progsMap.put(name, knownLangs);
		}
	}

	private void createLangsMap(List<String[]> progLang) {
		ArrayList<String> values;
		for (String[] line : progLang) {
			values = new ArrayList<>();
			for (int i = 1; i < line.length; i++) {
				if (!values.contains(line[i]))
					values.add(line[i]);
			}
			langsMap.put(line[0], values);
		}
	}

	public Map<String, ArrayList<String>> getLangsMap() {
		return langsMap;
	}

	public Map<String, ArrayList<String>> getProgsMap() {
		return progsMap;
	}

	public Map<String, ArrayList<String>> getLangsMapSortedByNumOfProgs() {

		return sorted(langsMap, (kv1, kv2) -> {
			return kv2.getValue().size() - kv1.getValue().size();
		});
	}

	public Map<String, ArrayList<String>> getProgsMapSortedByNumOfLangs() {
		return sorted(progsMap, (kv1, kv2) -> {
			return kv2.getValue().size() - kv1.getValue().size();
		});
	}

	public Map<String, ArrayList<String>> getProgsMapForNumOfLangsGreaterThan(int i) {
		return filtered(progsMap, kv -> {
			return kv.getValue().size() > i;
		});
	}

}
