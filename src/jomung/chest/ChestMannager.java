package jomung.chest;

import java.util.ArrayList;
import java.util.UUID;

public final class ChestMannager {

	private static ArrayList<UUID> ids = new ArrayList<>();

	private ChestMannager() {
	}

	public static void addChest(UUID id) {
		ids.add(id);
	}

	public static UUID addKey() {
		if (ids.size() != 0) {
			return ids.remove(0);
		}
		return null;
	}

}
