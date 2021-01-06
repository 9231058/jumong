package jomung.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import jomung.Room;
import jomung.res.R;

public class XMLMapFactory implements MapFactory {

	private Room[][] rooms = null;
	private ArrayList<Point> startPoints = new ArrayList<>();
	private Point endPoint = null;
	private int row = 0;
	private int column = 0;
	private String path = "XMLMap.xml";

	@Override
	public void create() {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = null;
		try {
			xmlEventReader = xmlInputFactory
					.createXMLEventReader(XMLMapFactory.class
							.getResourceAsStream(path));
		} catch (XMLStreamException exception) {
			exception.printStackTrace();
		}
		int counter = 0;
		while (xmlEventReader.hasNext()) {
			if (row != 0 && column != 0 && rooms == null) {
				rooms = new Room[row][column];
			}
			XMLEvent event = null;
			try {
				event = xmlEventReader.nextEvent();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart().equals("Table")) {
					@SuppressWarnings("unchecked")
					Iterator<Attribute> iterator = startElement.getAttributes();
					while (iterator.hasNext()) {
						Attribute attribute = iterator.next();
						if (attribute.getName().toString().equals("Row")) {
							row = Integer.parseInt(attribute.getValue());
						}
						if (attribute.getName().toString().equals("Column")) {
							column = Integer.parseInt(attribute.getValue());
						}
					}
				} else if (startElement.getName().getLocalPart().equals("Row")) {
					try {
						event = xmlEventReader.nextEvent();
					} catch (XMLStreamException exception) {
						exception.printStackTrace();
					}
					if (event.isCharacters()) {
						String rowString = event.asCharacters().toString()
								.trim();
						for (int j = 0; j < column; j++) {
							rooms[counter][j] = null;
							char roomChar = rowString.charAt(j);
							if (roomChar == '.') {
								rooms[counter][j] = new Room();
								rooms[counter][j].setWall(false);
							} else if (roomChar == '|') {
								rooms[counter][j] = new Room();
								rooms[counter][j].setWall(true);
							} else if (roomChar == 'S') {
								rooms[counter][j] = new Room();
								rooms[counter][j].setWall(false);
								startPoints.add(new Point(counter, j));
							} else if (roomChar == 'F') {
								rooms[counter][j] = new Room();
								rooms[counter][j].setWall(false);
								endPoint = new Point(counter, j);
							}
						}
						counter++;
					}

				}
			}
		}
		if (startPoints.size() == 0 || endPoint == null || rooms == null) {
			throw (new IllegalArgumentException());
		}
	}

	@Override
	public void setR() {
		R.getInstance().setSize(row, column);
		R.getInstance().setEndPoint(endPoint);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				R.getInstance().addRoom(i, j, rooms[i][j]);
			}
		}
		for (int i = 0; i < startPoints.size(); i++) {
			R.getInstance().addPlayer(startPoints.get(i).x,
					startPoints.get(i).y);
		}
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}
}
