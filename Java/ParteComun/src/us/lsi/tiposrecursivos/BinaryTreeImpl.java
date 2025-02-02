package us.lsi.tiposrecursivos;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import us.lsi.common.Lists2;
import us.lsi.common.Preconditions;
import us.lsi.common.Files2;
import us.lsi.common.Strings2;
import us.lsi.tiposrecursivos.BinaryPatternImpl.Matches;
import us.lsi.tiposrecursivos.parsers.BinaryTreeLexer;
import us.lsi.tiposrecursivos.parsers.BinaryTreeParser;
import us.lsi.tiposrecursivos.parsers.BinaryTreeVisitorC;


public class BinaryTreeImpl<E> implements MutableBinaryTree<E> {
	
	private static BinaryTreeImpl<Object> empty = new BinaryTreeImpl<Object>();
	
			
	@SuppressWarnings("unchecked")
	public static <E> BinaryTreeImpl<E> empty() {
		return (BinaryTreeImpl<E>) empty;
	}
	
	public static <E> BinaryTreeImpl<E> leaf(E label) {
		return new BinaryTreeImpl<E>(label);
	}
	
	public static <E> BinaryTreeImpl<E> binary(E label, BinaryTree<E> left, BinaryTree<E> right) {
		BinaryTreeImpl<E> r = null;
		Preconditions.checkNotNull(left);
		Preconditions.checkNotNull(right);
		if (left.isEmpty() && right.isEmpty()) {
			r = new BinaryTreeImpl<E>(label);
		} else {
			r = new BinaryTreeImpl<E>(label, (BinaryTreeImpl<E>)left, (BinaryTreeImpl<E>)right);
		}
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public static BinaryTree<String> parse(String s){
		BinaryTreeLexer lexer = new BinaryTreeLexer(CharStreams.fromString("-43.7(2.1,abc34(-27.3(_,2),78.2(3,4)))"));
		BinaryTreeParser parser = new BinaryTreeParser(new CommonTokenStream(lexer));
	    ParseTree parseTree = parser.binary_tree();
	    BinaryTree<String> tree =  (BinaryTree<String>) parseTree.accept(new BinaryTreeVisitorC());
	    return tree;
	}
	
	public static <E> BinaryTree<E> parse(String s, Function<String,E> f) {
		Preconditions.checkNotNull(s);
		BinaryTree<String> tree = BinaryTreeImpl.parse(s);
		return tree.map(f);
	}
	

	protected E label;
	protected BinaryTreeImpl<E> left;
	protected BinaryTreeImpl<E> right;
	protected BinaryTreeImpl<E> father;
	private final BinaryType type;
	

	protected BinaryTreeImpl() {
		super();
		this.label = null;
		this.left = null;
		this.right = null;
		this.type = BinaryType.Empty;
		this.father = null;
	}

	
	protected BinaryTreeImpl(E label) {
		super();
		this.label = label;
		this.left = null;
		this.right = null;		
		this.type = BinaryType.Leaf;
		this.father = null;
	}
	
	protected BinaryTreeImpl(E label, BinaryTreeImpl<E> left, BinaryTreeImpl<E> right) {
		super();
		this.label = label;
		this.left = left;
		this.right = right;
		this.type = BinaryType.Binary;	
		this.father = null;
		this.left.father = this;
		this.right.father = this;
	}

	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return type.equals(BinaryType.Empty);
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return type.equals(BinaryType.Leaf);
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isBinary()
	 */
	@Override
	public boolean isBinary() {
		return type.equals(BinaryType.Binary);
	}	
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getType()
	 */
	@Override
	public BinaryType getType() {
		return type;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.IBinaryTree#getFather()
	 */
	@Override
	public BinaryTree<E> getFather() {
		return this.father;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isRoot()
	 */
	@Override
	public boolean isRoot() {
		return this.father == null;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isLeftChild()
	 */
	@Override
	public Boolean isLeftChild() {
		Boolean r;
		if(isRoot()) {
			r = false;
		} else {
			r = getFather().getLeft() == this;
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#isRightChild()
	 */
	@Override
	public Boolean isRightChild() {
		Boolean r;
		if(isRoot()) {
			r = false;
		} else {
			r = getFather().getRight() == this;
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getChildType()
	 */
	@Override
	public ChildType getChildType() {
		ChildType r = null;
		if(isRoot()) {
			r = ChildType.Root;
		} else if(this.getFather().getLeft() == this) {
			r = ChildType.Left;
		} else if(this.getFather().getRight() == this) {
			r = ChildType.Right;
		} else {
			Preconditions.checkState(false, "Fallo interno");
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getLabel()
	 */
	@Override
	public E getLabel() {
		Preconditions.checkState(!isEmpty(), String.format("El �rbol es vac�o"));
		return this.label;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getLeft()
	 */
	@Override
	public BinaryTreeImpl<E> getLeft(){
		Preconditions.checkState(isBinary(), String.format("El �rbol no es binario"));
		return this.left;
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getRight()
	 */
	@Override
	public BinaryTreeImpl<E> getRight(){
		Preconditions.checkState(isBinary(), String.format("El �rbol no es binario"));
		return this.right;
	}
	
	public void setLabel(E label) {
		this.label = label;
	}

	public void setLeft(BinaryTree<E> left) {
		BinaryTreeImpl<E> tt = (BinaryTreeImpl<E>) left;
		this.left = tt;
		tt.father = this;
	}
	
	public void setRight(BinaryTree<E> right) {
		BinaryTreeImpl<E> tt = (BinaryTreeImpl<E>) right;
		this.right = tt;
		tt.father = this;
	}
	
	public void setFather(BinaryTree<E> father) {
		BinaryTreeImpl<E> tt = (BinaryTreeImpl<E>) father;
		this.father = tt;
	}
	
	/**
	 * @post this pasa a ser un arbol raiz si no lo era antes. nTree pasa a ocupar el lugar de this. El padre de nTree
	 * es el antiguo padre de this
	 * @param nTree Un �rbol binario
	 * @return  Devuelve nTree
	 */
	public BinaryTree<E> changeFor(BinaryTree<E> nTree) {
		BinaryTreeImpl<E> tt = (BinaryTreeImpl<E>) nTree;
		switch(this.getChildType()) {
		case Root: break;
		case Left: this.father.left = tt; break;
		case Right: this.father.right = tt ; break;
		}
		return tt;
	}
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#mutableView()
	 */
	@Override
	public MutableBinaryTree<E> mutableView(){
		return (MutableBinaryTree<E>) this;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#size()
	 */
	@Override
	public int size() {
		int r =0;
		switch(this.getType()) {
		case Empty: r = 0; break;
		case Leaf:  r = 1; break;
		case Binary: r = 1+this.getLeft().size()+ this.getRight().size(); break;
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getHeight()
	 */
	@Override
	public int getHeight() {
		Integer r=0;
		switch(this.getType()) {
		case Empty: r = -1; break;
		case Leaf:  r = 0; break;
		case Binary: r = 1+ Math.max(this.getLeft().getHeight(),this.getRight().getHeight()); break;
		}
		return r;
	}
	
	private static <E> List<BinaryTree<E>> nextLevel(List<BinaryTree<E>> ls){
		List<BinaryTree<E>> r = Lists2.empty();
		for(BinaryTree<E> tree: ls) {
			switch(tree.getType()) {			
			case Empty:
			case Leaf: r.add(BinaryTree.empty()); r.add(BinaryTree.empty()); break;
			case Binary: r.add(tree.getLeft()); r.add(tree.getRight()); break;
			}
		}
		return r;
	}
	
	
	@Override
	public List<BinaryTree<E>> getLevel(int n){
		List<BinaryTree<E>> r = Lists2.of(this);
		for(int i=0; i < n ; i++) {
			r = nextLevel(r);
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getHeights(int)
	 */
	@Override
	public List<Integer> getHeights(int n){
		return this.getLevel(n).stream().map(x->x.getHeight()).collect(Collectors.toList());
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#copy()
	 */
	@Override
	public BinaryTree<E> copy() {
		BinaryTree<E> r= null;
		switch(this.getType()) {
		case Empty: r = BinaryTree.empty(); break;
		case Leaf:  r = BinaryTree.leaf(label); break;
		case Binary: r = BinaryTree.binary(label,this.getLeft().copy(),this.getRight().copy()); break;
		}
		return r;
	}


	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getReverse()
	 */
	@Override
	public BinaryTree<E> getReverse() {
		BinaryTree<E> r= null;
		switch(this.getType()) {
		case Empty: r = BinaryTree.empty(); break;
		case Leaf:  r = BinaryTree.leaf(label); break;
		case Binary: r = BinaryTree.binary(label,this.getRight().copy(),this.getLeft().copy()); break;
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#map(java.util.function.Function)
	 */
	@Override
	public <R> BinaryTree<R> map(Function<E,R> f){
		BinaryTree<R> r = null;
		switch(this.getType()) {	
		case Empty: r = BinaryTree.empty(); break;
		case Leaf: r = BinaryTree.leaf(f.apply(this.getLabel())); break;
		case Binary: r = BinaryTree.binary(f.apply(this.getLabel()), this.getLeft().map(f), this.getRight().map(f)); break;
		}
		return r;
	}	
		
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#transform(us.lsi.tiposrecursivos.BinaryPattern, us.lsi.tiposrecursivos.BinaryPattern)
	 */
	@Override
	public BinaryTree<E> transform(BinaryPattern<E> pattern, BinaryPattern<E> result){
		return BinaryPattern.transform(this, pattern, result);
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#match(us.lsi.tiposrecursivos.BinaryPattern)
	 */
	@Override
	public Matches<E> match(BinaryPattern<E> pt) {
		return BinaryPattern.match(this, pt);
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#toString()
	 */
	@Override
	public String toString() {
		String r = null;
		switch (this.getType()) {
		case Empty: r ="_"; break;
		case Leaf: r = label.toString(); break;
		case Binary: r = label.toString()+"("+this.getLeft().toString()+","+this.getRight().toString()+")";
		}
		return r;
	}
	
	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getPreOrder()
	 */
	@Override
	public List<E> getPreOrder() {
		List<E> r = null;
		switch (this.getType()) {
		case Empty: r = Lists2.empty(); break;
		case Leaf: r = Lists2.of(this.label); break;
		case Binary:
			r = Lists2.of(this.label);
			r.addAll(this.getLeft().getPreOrder());
			r.addAll(this.getRight().getPreOrder());
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getPostOrder()
	 */
	@Override
	public List<E> getPostOrder() {
		List<E> r = null;
		switch (this.getType()) {
		case Empty: r = Lists2.empty(); break;
		case Leaf: r = Lists2.of(this.label); break;
		case Binary:
			r = this.getLeft().getPostOrder();
			r.addAll(this.getRight().getPostOrder());
			r.add(this.getLabel());
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see us.lsi.tiposrecursivos.BinaryTree#getInOrder()
	 */
	@Override
	public List<E> getInOrder() {
		List<E> r = null;
		switch (this.getType()) {
		case Empty: r = Lists2.empty(); break;
		case Leaf: r = Lists2.of(this.label); break;
		case Binary:
			r = this.getLeft().getInOrder();
			r.add(this.getLabel());
			r.addAll(this.getRight().getInOrder());			
		}
		return r;
	}
	
	public BinaryTree<E> equilibrate() {
		return equilibrate(this);
	}
	
	public static <E> BinaryTree<E> equilibrate(BinaryTree<E> tree) {
		Patterns<E> pt = Patterns.of();
		BinaryTree<E> r = null;
		switch(getTypeEquilibrate(tree)) {
		case Equilibrate: r = tree; break;
		case LeftLeft: r = tree.transform(pt.leftLeft,pt.result);	break;
		case LeftRight: r = tree.transform(pt.leftRight,pt.result); break;
		case RightLeft: r = tree.transform(pt.rightLeft,pt.result);	break;
		case RightRight: r = tree.transform(pt.rightRight,pt.result); break;
		}
		return r;
	}   
	
	public enum TypeEquilibrate{LeftRight, LeftLeft, RightLeft, RightRight, Equilibrate} 
	
	public static <E> TypeEquilibrate getTypeEquilibrate(BinaryTree<E> tree) {
		TypeEquilibrate r = null;
		List<Integer> n1 = tree.getHeights(1);
		List<Integer> n2 = tree.getHeights(2);
		int left = n1.get(0);
		int right = n1.get(1);
		int leftleft = n2.get(0);
		int leftright = n2.get(1);
		int rightleft = n2.get(2);
		int rightright = n2.get(3);
		if (Math.abs(left - right) < 2) {
			r = TypeEquilibrate.Equilibrate;
		} else if (left - right >= 2) {
			if (leftleft >= leftright) {
				r = TypeEquilibrate.LeftLeft;
			} else {
				r = TypeEquilibrate.LeftRight;
			}
		} else if (left - right < 2) {
			if (rightleft >= rightright) {
				r = TypeEquilibrate.RightLeft;
			} else {
				r = TypeEquilibrate.RightRight;
			}
		}
		Preconditions.checkArgument(r != null, String.format("%d,%d,%d,%d,%d,%d,%s", left, right, leftleft, leftright,
				rightleft, rightright, tree.toString()));
		return r;
	}
	
	
	static class Patterns<R> {
		BinaryPattern<R> leftRight; 
	    BinaryPattern<R> rightLeft;
	    BinaryPattern<R> leftLeft;
	    BinaryPattern<R> rightRight;
	    BinaryPattern<R> result;
	    private static Patterns<?> patterns = null;
	    @SuppressWarnings("unchecked")
		public static <R> Patterns<R> of(){
	    	if(patterns==null) patterns = new Patterns<R>();
	    	return (Patterns<R>)patterns;
	    }
	    public Patterns() {
			super();
			this.leftRight = BinaryPattern.parse("_e5(_e3(_A,_e4(_B,_C)),_D)");
			this.rightLeft = BinaryPattern.parse("_e3(_A,_e5(_e4(_B,_C),_D))");
			this.leftLeft = BinaryPattern.parse("_e5(_e4(_e3(_A,_B),_C),_D)");
			this.rightRight = BinaryPattern.parse("_e3(_A,_e4(_B,_e5(_C,_D)))");
			this.result = BinaryPattern.parse("_e4(_e3(_A,_B),_e5(_C,_D))");
		}
	    
	}

	
	public static void test1() {
		List<String> filas = Files2.streamFromFile("ficheros/test2.txt").collect(Collectors.toList());
		BinaryTree<String> tree = null;
		for (String fila : filas) {
			tree = BinaryTreeImpl.parse(fila);
			System.out.println(tree);
		}
	}
	
	public static void main(String[] args) {
		BinaryTree<Integer> t1 = BinaryTree.empty();
		BinaryTree<Integer> t2 = BinaryTree.leaf(2);
		BinaryTree<Integer> t3 = BinaryTree.leaf(3);
		BinaryTree<Integer> t4 = BinaryTree.leaf(4);
		BinaryTree<Integer> t5 = BinaryTree.binary(27,BinaryTree.binary(42,t1,t2),BinaryTree.binary(59,t3,t4));
		BinaryTree<Integer> t6 = BinaryTree.binary(39, t2,t5);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t6);		
		String ex = "-43.7(2.1,abc(-27.3(_,2),78.2(3,4)))";
		BinaryTree<String> t7 = BinaryTree.parse(ex);
		System.out.println(t7);
		System.out.println(Lists2.reverse(Lists2.of(1,2,3,4,5,6,7,8,9)));
		BinaryTree<String> t8 = t7.getReverse();
		System.out.println(t8);
		MutableBinaryTree<String> t = t8.mutableView();
		t.setLabel("578.");
		t.setLeft(t8.getLeft().getLeft());
		System.out.println(t8);
		Strings2.toConsole(t8.getLevel(3).stream().map(x->x.getHeight()).collect(Collectors.toList()).toString());
		BinaryTree<String> t10 = t8.getRight();
		System.out.println(t8);
		System.out.println(t10);
		System.out.println(t10.getFather().getRight() == t10);
		System.out.println(t10.isRightChild());
		MutableBinaryTree<String> mt10 = t10.mutableView();
		mt10.changeFor(t8.getLeft());
		System.out.println(t8);
		System.out.println(t10);
		System.out.println(t8.isRoot());
		System.out.println(t10.isRoot());
		test1();
		BinaryTree<Double> tree1 = BinaryTree.parse("54.5(39.2(20.1,50.5(40.2,51.0)),78.9)",x->Double.parseDouble(x));
		BinaryTree<Double> tree2 = BinaryTree.parse("54.5(39.2,20.1(50.5(40.2,51.0),78.9))",x->Double.parseDouble(x));
        BinaryTree<Double> tree3 = BinaryTree.parse("54.5(39.2(20.1(50.5,40.2),51.0),78.9)",x->Double.parseDouble(x));
        BinaryTree<Double> tree4 = BinaryTree.parse("54.5(39.2,20.1(50.5,40.2(51.0,78.9)))",x->Double.parseDouble(x));
        var tree1r = tree1.equilibrate();
		System.out.println("Aqui 1 = "+tree1r);
		var tree2r = tree2.equilibrate();
		System.out.println("Aqui 2 = "+tree2r);
		var tree3r = tree3.equilibrate();
		System.out.println("Aqui 3 = "+tree3r);
		var tree4r = tree4.equilibrate();
		System.out.println("Aqui 4 = "+tree4r);
	}

}
