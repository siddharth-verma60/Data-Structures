package Trie;

import java.util.HashMap;

public class Trie {
	private class Node {
		Character data;
		boolean isTerminal;
		HashMap<Character, Node> children;

		Node(Character data, boolean isTerminal) {
			this.data = data;
			this.isTerminal = isTerminal;
			children = new HashMap<>();
		}
	}

	private int size;
	private Node root;

	public Trie() {
		this.root = new Node('\0', false);
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public boolean containsWord(String word) {
		Node temp = this.root;

		for (int i = 0; i < word.length(); i++) {
			Node child = temp.children.get(i);

			if (child == null) {
				return false;
			}

			temp = child;
		}
		return temp.isTerminal;
	}

	public void addWord(String word) {
		this.addWord(this.root, word);
	}

	private void addWord(Node node, String word) {
		if (word.length() == 0) {
			if (node.isTerminal) {
				return;
			}
			this.size++;
			node.isTerminal = true;
			return;
		}

		Node child = node.children.get(word.charAt(0));

		if (child == null) {
			child = new Node(word.charAt(0), false);
			node.children.put(word.charAt(0), child);
		}

		this.addWord(child, word.substring(1));
	}

	public void removeWord(String word) {
		this.removeWord(this.root, word);
	}

	private void removeWord(Node node, String word) {
		if (word.length() == 0) {
			if (!node.isTerminal) {
				return;
			}
			this.size--;
			node.isTerminal = false;
			return;
		}

		Node child = node.children.get(word.charAt(0));

		if (child == null) {
			return;
		}

		this.removeWord(child, word.substring(1));

		if (!child.isTerminal && child.children.isEmpty()) {
			node.children.remove(child.data);
		}
	}

}
