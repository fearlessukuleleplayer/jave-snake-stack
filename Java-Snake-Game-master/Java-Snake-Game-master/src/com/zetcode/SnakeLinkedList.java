package com.zetcode;

import java.awt.Color;

public class SnakeLinkedList {
	SnakeNode head = null;

	public void addHead(int x, int y, Color c) {
		head = new SnakeNode(x, y, c);
	}

	public void addJoint(int x, int y, Color c) {
		SnakeNode newJoint = new SnakeNode(x, y, c);
		newJoint.setNext(head.getNext());
		head.setNext(newJoint);
	}

	public void addTail(Color c) {
		SnakeNode curr = head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		SnakeNode newJoint = new SnakeNode(curr.getX() + 10, curr.getY() + 10, c);
		curr.setNext(newJoint);
	}

	public SnakeNode getHead() {
		return head;
	}

	public SnakeNode getJoint(int i) {
		SnakeNode curr = head;
		for (int k = 0; k < i; k++) {
			curr = curr.getNext();
		}
		return curr;
	}

	public void addFirst(SnakeNode node) {
		node.setNext(head);
		head = node;
	}

	public void addLast(SnakeNode node) {
		if (head == null) {
			head = node;
			return;
		}
		SnakeNode curr = head;
		while (curr.getNext() != null) {
			curr = curr.getNext();
		}
		curr.setNext(node);
	}

	public void snakeMove(int dots, boolean left, boolean right, boolean up, boolean down, final int SIZE) {
		int arrSize = dots - 1; // because of the head
		for (int z = arrSize; z > 0; z--) {
			if (z == 1) {
				getJoint(z).setX(getHead().getX());
				getJoint(z).setY(getHead().getY());
			}
			getJoint(z).setX(getJoint(z - 1).getX());
			getJoint(z).setY(getJoint(z - 1).getY());
		}
		if (left) {
			int i = getHead().getX();
			i -= SIZE;
			getHead().setX(i);
		}
		if (right) {
			int i = getHead().getX();
			i += SIZE;
			getHead().setX(i);
		}
		if (up) {
			int i = getHead().getY();
			i -= SIZE;
			getHead().setY(i);
		}
		if (down) {
			int i = getHead().getY();
			i += SIZE;
			getHead().setY(i);
		}
	}
	
	public boolean snakeCollision(int bottom, int right, boolean inGame, int dots) {
		for(int z = 1; z < dots - 1; z++) {
			if (getHead().getX() == getJoint(z).getX() && getHead().getY() == getJoint(z).getY()) {
				inGame = false;
			}
		}
		if (getHead().getY() >= bottom) {
			inGame = false;
		}
		if (getHead().getY() < 0) {
			inGame = false;
		}
		if (getHead().getX() >= right) {
			inGame = false;
		}
		if (getHead().getX() < 0) {
			inGame = false;
		}
	return inGame;
	}
	
	public boolean apple(int apple_x, int apple_y) {
		if ((getHead().getX() == apple_x) && (getHead().getY() == apple_y)) {
			return true;
		}
		return false;
	} 
}

