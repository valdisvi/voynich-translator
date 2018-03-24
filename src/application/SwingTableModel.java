package application;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SwingTableModel extends AbstractTableModel {

	Transliteration chosenTransliteration = (Transliteration) SwingApp.a;
	LinkedHashMap<String, String> transliterationRules = chosenTransliteration.getList();
	private Map<String, String> data = transliterationRules;
	private String[] keys;

	public SwingTableModel() {
		keys = data.keySet().toArray(new String[data.size()]);
	}

	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "From";
		} else {
			return "To";
		}
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return keys[row];
		} else {
			return data.get(keys[row]);
		}
	}
}