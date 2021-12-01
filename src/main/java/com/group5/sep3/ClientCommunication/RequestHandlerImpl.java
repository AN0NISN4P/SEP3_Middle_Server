package com.group5.sep3.ClientCommunication;

import com.group5.sep3.BusinessLogic.LogicModels.LogicModel;
import com.group5.sep3.BusinessLogic.LogicModels.impl.ItemModelImpl;
import com.group5.sep3.BusinessLogic.LogicModels.impl.LocationModelImpl;
import com.group5.sep3.BusinessLogic.model.Item;
import com.group5.sep3.BusinessLogic.model.Location;
import com.group5.sep3.ClientCommunication.SocketCommunication.TransferObjects.Request;
import com.group5.sep3.ClientCommunication.SocketCommunication.TransferObjects.RequestType;
import com.group5.sep3.LogicModelFactory;
import com.group5.sep3.util.EntityTypes;
import com.group5.sep3.util.ProjectUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class RequestHandlerImpl implements RequestHandler, PropertyChangeListener {

    PropertyChangeListener propertyChangeListener;

    private final HashMap<EntityTypes, LogicModel> requestHandlerMap;


    public RequestHandlerImpl() {
        requestHandlerMap = new HashMap<>();
        requestHandlerMap.putAll(LogicModelFactory.getInstance().getAll());
    }

    @Override
    public Object handleRequest(Request request) throws Exception {

        String objectClassName = request.getClassName();

        ProjectUtil.testPrint("Request Class: " + objectClassName + "\nRequest Handler Keys: " + requestHandlerMap.keySet());

        switch (objectClassName) {
            case "Item" -> {
                return handleItem(request.getType(), request.getArg(Item.class));
            }
            case "Location" -> {
                return handleLocation(request.getType(), request.getArg(Location.class));
            }
        }
        throw new Exception("Object Class Not Found");
    }

    private Object handleItem(RequestType requestType, Item item) {
        ItemModelImpl model = (ItemModelImpl) requestHandlerMap.get(EntityTypes.Item);
        switch (requestType) {
            case GET -> {
                return model.get(item);
            }
            case GETALL -> {
                return model.getAll();
            }
            case PUT -> {
                return model.register(item);
            }
            case POST -> {
                return model.update(item);
            }
            case DELETE -> {
                return model.remove(item);
            }
        }
        return null;
    }

    private Object handleLocation(RequestType requestType, Location loc) {
        LocationModelImpl model = (LocationModelImpl) requestHandlerMap.get(EntityTypes.Location);
        switch (requestType) {
            case GET -> {
                return model.get(loc);
            }
            case GETALL -> {
                return model.getAll();
            }
            case PUT -> {
                return model.register(loc);
            }
            case POST -> {
                return model.update(loc);
            }
            case DELETE -> {
                return model.remove(loc);
            }
        }
        return null;
    }


    @Override
    public void addListener(String name, PropertyChangeListener listener) {
        ProjectUtil.notImplemented();
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        this.propertyChangeListener = listener;
    }

    @Override
    public void removeListener(String name, PropertyChangeListener listener) {
        ProjectUtil.notImplemented();
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        ProjectUtil.notImplemented();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ProjectUtil.notImplemented();
    }
}
