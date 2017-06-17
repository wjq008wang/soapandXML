package jaxb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapAdapter.AdapterMap, Map<String, Object>> {

	public static class AdapterMap {
		public List<Entry> entry = new ArrayList<Entry>();
	}

	/**
	 * 功能:xml->Map<String,Object>
	 * 
	 * @param adapterMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> unmarshal(AdapterMap adapterMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List entry = adapterMap.entry;
		for (Entry en : adapterMap.entry) {
			map.put(en.getKey(), en.getValue());
		}
		return map;
	}

	/**
	 * 功能:map<String,Object> -> xml
	 */
	@Override
	public AdapterMap marshal(Map<String, Object> map) throws Exception {
		AdapterMap adapterMap = new AdapterMap();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Entry entry2 = new Entry();
			entry2.setKey(entry.getKey());
			entry2.setValue(entry.getValue());
			adapterMap.entry.add(entry2);
		}
		return adapterMap;
	}

}