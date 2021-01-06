package jomung.system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class SysR implements Serializable {
	private static final long serialVersionUID = 7126456703358885654L;
	private static SysR instance = null;
	private static String path;
	private HashMap<Class<? extends Object>, Serializable> objects;

	public static SysR getInstance() {
		if (instance == null) {
			instance = new SysR();
		}
		return instance;
	}

	public static void setPath(String newPath) {
		path = newPath;
	}

	private SysR() {
		objects = new HashMap<Class<? extends Object>, Serializable>();
	}

	public void addObject(Serializable object,
			Class<? extends Object> objectClass) {
		objects.put(objectClass, object);
	}

	public Object getObject(Class<? extends Object> objectClass) {
		Object object = null;
		try {
			object = objects.get(objectClass);
		} catch (ClassCastException exception) {

		}
		return object;
	}

	public void finish() {
		try {
			ObjectOutputStream ostream = new ObjectOutputStream(
					new FileOutputStream(path));
			ostream.writeObject(this);
			ostream.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void start() {
		try {
			ObjectInputStream istream = new ObjectInputStream(
					new FileInputStream(path));
			objects = ((SysR) istream.readObject()).objects;
			istream.close();
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}
