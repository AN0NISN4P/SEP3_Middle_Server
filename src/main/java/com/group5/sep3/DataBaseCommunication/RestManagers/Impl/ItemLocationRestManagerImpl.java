package com.group5.sep3.DataBaseCommunication.RestManagers.Impl;

import com.google.gson.reflect.TypeToken;
import com.group5.sep3.BusinessLogic.model.ItemLocation;
import com.group5.sep3.DataBaseCommunication.RestClientImpl;
import com.group5.sep3.DataBaseCommunication.RestManagers.ItemLocationRestManager;
import com.group5.sep3.util.JsonHelper;
import org.springframework.web.client.RestClientException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemLocationRestManagerImpl implements ItemLocationRestManager {
	@Override
	public ItemLocation put(ItemLocation obj) {
		String restUrl = obj.getClass().getSimpleName();
		String restResponse = (String) RestClientImpl.getInstance().put(restUrl, obj);

		return JsonHelper.fromJson(restResponse, ItemLocation.class);
	}

	@Override
	public ItemLocation post(ItemLocation obj) {
		String restUrl = obj.getClass().getSimpleName();
		String restResponse = (String) RestClientImpl.getInstance().post(restUrl, obj);

		return JsonHelper.fromJson(restResponse, ItemLocation.class);
	}

	@Override
	public ItemLocation get(ItemLocation obj) {
		String restUrl = obj.getClass().getSimpleName() + "/" + obj.getId();
		String restResponse = (String) RestClientImpl.getInstance().get(restUrl);

		return JsonHelper.fromJson(restResponse, ItemLocation.class);
	}

	@Override
	public Collection<ItemLocation> getAll() {
		String restUrl = ItemLocation.class.getSimpleName();

		String restResponse = (String) RestClientImpl.getInstance().get(restUrl);

		return getItemLocationsFromResponse(restResponse);
	}

	@Override
	public ItemLocation delete(ItemLocation obj) {
		String restUrl = ItemLocation.class.getSimpleName() + "/" + obj.getId();

		return RestClientImpl.getInstance().delete(restUrl) ? obj : null;
	}

	@Override
	public List<ItemLocation> getByItemId(ItemLocation obj) throws RestClientException {
		String restUrl = obj.getClass().getSimpleName() + "/itemId/" + obj.getItem().getId();
		String restResponse = (String) RestClientImpl.getInstance().get(restUrl);
		return getItemLocationsFromResponse(restResponse);
	}

	@Override
	public List<ItemLocation> getByLocationId(ItemLocation obj) throws RestClientException {
		String restUrl = obj.getClass().getSimpleName() + "/locationId/" + obj.getLocation().getId();
		String restResponse = (String) RestClientImpl.getInstance().get(restUrl);
		return getItemLocationsFromResponse(restResponse);
	}

	private List<ItemLocation> getItemLocationsFromResponse(String restResponse) {
		Type type = new TypeToken<ArrayList<ItemLocation>>() {}.getType();
		return JsonHelper.fromJson(restResponse, type);
	}
}
