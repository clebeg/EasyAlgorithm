package io.github.clebeg.algo.model.tree;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 红黑树
 * <p> 学习地址：http://www.cainiaoxueyuan.com/suanfa/15196.html
 * <p> 在线演示地址：<a href="https://rbtree.phpisfuture.com/">https://rbtree.phpisfuture.com/</a>
 * <p> 通过 234树 和 红黑树的等价关系，彻底搞明白红黑树的实现原理 </p>
 * <p> 理解红黑树之前，得先弄清楚什么是二叉查找树 </p>
 * <p> 二叉查找树：
 * <ul>
 *     <li>1. 根节点的值大于左子树所有节点的值</li>
 *     <li>2. 根节点的值小于右子树所有节点的值</li>
 *     <li>3. 左右子树具有同样的性质</li>
 * </ul>
 * <p>原始的二叉查找树极端情况下会退化为链表，影响查找效率，所以需要优化，常见的优化结构是：AVL树和红黑树</p>
 * <p>AVL树比较严格，要求每个结点的左右子树的高度之差不超过 1，导致数据在插入的时候需要频繁进行调整</p>
 * <p>红黑树只要求从一个节点出发，黑色节点数量相等，从而在提高查询效率同时，减少维护成本，使用比较广泛。</p>
 * <p>红黑树的插入、删除操作都比较复杂，直接记忆难度很大，通过234树可以深入理解红黑树的本质原理。</p>
 * <p>一颗红黑树对应唯一的234树，而一颗234树对应多颗红黑树。</p>
 * <p>234树：是一颗满树，并且节点元素个数为123个，从而导致它们对应的分叉分别为234，所以成为234树。</p>
 * <pre>
 * <code>
 *        5
 *     /     \
 *    3      (79)
 *   / \    / | \
 * (12) 4  6 8  (10 11 12)
 * </code>
 * <p>234树和红黑树对应关系如下</p>
 * <ul>
 *     <li>1. 2节点对应黑色节点</li>
 *     <li>2. 3节点对应上黑下红，如果上大，下红就是左儿子，如果上小，下红就是右儿子</li>
 *     <li>3. 4节点对应上黑下两红</li>
 * </ul>
 * </pre>
 *
 * <p>红黑树增加节点相当于234树增加元素，需要分几种情况，具体在方法中备注。</p>
 * <p>红黑树删除节点相当于234树删除元素，需要分几种情况，具体在方法中备注。</p>
 *
 * <p>红黑树的性质，不用死记硬背，结合234树很容易得出。</p>
 * <ul>
 *     <li>1. 根节点一定是黑色节点</li>
 *     <li>2. 叶子节点也一定是黑色节点</li>
 *     <li>3. 红色节点的两个子节点一定是黑色节点</li>
 *     <li>4. 从给定节点到叶子节点的所有路径的黑色节点数量相等</li>
 *     <li>5. 所有节点要么黑色要么红色</li>
 * </ul>
 * @author clebegxie
 *
 */
public class RBTree<K extends Comparable, V> {
    // 用 true 表示黑色，false 表示红色
    private static final boolean BLACK = true;
    private static final boolean RED = false;
    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    /**
     * 红黑树的节点，有指针指向父节点，和左右子节点
     * 节点的值用 key value 对表示
     * 颜色用 color 表示
     * @param <K> key
     * @param <V> value
     */
    class RBNode<K extends Comparable, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private K key;
        private V value;
        private boolean color = BLACK;

        public RBNode() {
        }

        public RBNode(K key, V value, boolean color) {
            this.key = key;
            this.value = value == null ? (V) key : value;
            this.color = color;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value == null ? (V) key : value;;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }

    /**
     * 红黑树的删除，首先需要按二叉树删除方式，找到待删除节点，然后转换为以下几种情况
     * 1. 删除一个最多只有一个孩子的结点的情况
     * 2. 删除节点有两个儿子，用前继节点或者后继节点代替后，删除后继节点，此时会回到情况1
     * 所以重点就是想办法解决情况1，这种情况也是类比234树进行相应讨论。
     * 删除一个节点，如果刚好是 3节点和4节点，直接删除，如果不巧，删除的是2节点，此时情况就复杂了
     * 1.1 如果兄弟是一个3或者4节点，则兄弟推一个给父节点，父节点下去代替需要删除的节点
     * 1.2 如果兄弟是一个2节点，那么父亲就下去和两个儿子合并，删除需要删除的节点，然后递归处理父节点
     * @param key
     */
    public V remove(K key) {
        RBNode point = root;
        while (point != null) {
            int cmp = point.getKey().compareTo(key);
            if (cmp == 0) {
                break;
            } else if (cmp > 0) {
                // 当前值大于需要删除的值
                point = point.left;
            } else {
                point = point.right;
            }
        }
        if (point == null) {
            return null;
        }
        V oldValue = (V) point.getValue();
        deleteNode(point);
        return oldValue;
    }

    private void deleteNode(RBNode point) {
        RBNode node = point;
        RBNode replaceNode;
        // 如果当前需要删除的节点是中间节点
        if (getLeft(point) != null && getRight(point) != null) {
            // 需要找到后继或者前驱代替
//            replaceNode = successorNode(point);
            replaceNode = predecessorNode(point);
            point.setKey(replaceNode.getKey());
            point.setValue(replaceNode.getValue());
            node = replaceNode;
        }
        // 运行到此处的 node 只可能有一个儿子
        replaceNode = getLeft(node) != null ? getLeft(node) : getRight(node);

        if (replaceNode != null) {
            // 执行替换并将node脱离
            transferParent(node, replaceNode);
            setRight(node, null);
            setLeft(node, null);
            setParent(node, null);

            if (node.color == BLACK) {
                // 替换节点的颜色一定是红色，只需要变色即可
                adjustAfterRemove(replaceNode);
            }
        } else if (getParent(node) == null) {
            // 删除根节点
            root = null;
        } else {
            if (node.color == BLACK) {
                adjustAfterRemove(node);
            }
            // 然后再脱离父亲，删除
            RBNode pNode = getParent(node);
            if (getLeft(pNode) == node) {
                setLeft(pNode, null);
            } else {
                setRight(pNode, null);
            }
            setParent(node, null);
        }
    }

    /**
     * 删除节点之后做相应的调整
     * @param node
     */
    private void adjustAfterRemove(RBNode node) {
        // 自己是二节点，需要和兄弟借
        while (node != root && getColor(node) == BLACK) {
            // 如果自己是左孩子
            if (node == getLeft(getParent(node))) {
                RBNode bNode = getRight(getParent(node));

                // 如果兄弟的颜色是红色，说明不是真正的兄弟
                if (getColor(bNode) == RED) {
                    // 左旋变色，对应同一颗234树
                    setColor(getParent(node), RED);
                    setColor(bNode, BLACK);
                    leftRotate(getParent(node));
                    // 得到真正的兄弟
                    bNode = getRight(getParent(node));
                }
                // 兄弟没得借，那只能把兄弟变红
                if (getColor(getLeft(bNode)) == BLACK && getColor(getRight(bNode)) == BLACK) {
                    setColor(bNode, RED);
                    // 递归向上处理
                    node = getParent(node);
                } else {
                    // 兄弟可以借，此时分3节点和4节点讨论
                    if (getColor(getRight(bNode)) == BLACK) {
                        // 三节点，想办法变为4节点
                        setColor(getLeft(bNode), BLACK);
                        setColor(bNode, RED);
                        rightRotate(bNode);
                        bNode = getRight(getParent(node));
                    }
                    // 4节点统一处理
                    setColor(bNode, getColor(getParent(node)));
                    setColor(getParent(node), BLACK);
                    setColor(getRight(bNode), BLACK);
                    // 一次性就借两个
                    leftRotate(getParent(node));
                    node = root;
                }
            } else {
                RBNode bNode = getLeft(getParent(node));

                // 如果兄弟的颜色是红色，说明不是真正的兄弟
                if (getColor(bNode) == RED) {
                    // 左旋变色，对应同一颗234树
                    setColor(getParent(node), RED);
                    setColor(bNode, BLACK);
                    rightRotate(getParent(node));
                    // 得到真正的兄弟
                    bNode = getLeft(getParent(node));
                }
                // 兄弟没得借，那只能把兄弟变红
                if (getColor(getLeft(bNode)) == BLACK && getColor(getRight(bNode)) == BLACK) {
                    setColor(bNode, RED);
                    // 递归向上处理
                    node = getParent(node);
                } else {
                    // 兄弟可以借，此时分3节点和4节点讨论
                    if (getColor(getLeft(bNode)) == BLACK) {
                        // 三节点，想办法变为4节点
                        setColor(getRight(bNode), BLACK);
                        setColor(bNode, RED);
                        leftRotate(bNode);
                        bNode = getLeft(getParent(node));
                    }
                    // 4节点统一处理
                    setColor(bNode, getColor(getParent(node)));
                    setColor(getParent(node), BLACK);
                    setColor(getLeft(bNode), BLACK);
                    // 一次性就借两个
                    rightRotate(getParent(node));
                    node = root;
                }
            }

        }
        // 如果是红色，就设置为黑色，毕竟删了一个黑色，还回去
        setColor(node, BLACK);
    }

    private RBNode predecessorNode(RBNode node) {
        RBNode curNode = getLeft(node);
        while (getRight(curNode) != null) {
            curNode = getRight(curNode);
        }
        if (curNode == null) {
            // 真正执行是不会走到这个逻辑
            RBNode sonNode = node;
            curNode = getParent(sonNode);
            while (getLeft(curNode) != sonNode) {
                sonNode = curNode;
                curNode = getParent(sonNode);
            }
        }
        return curNode;
    }

    private RBNode successorNode(RBNode node) {
        RBNode curNode = getRight(node);
        while (getLeft(curNode) != null) {
            curNode = getLeft(curNode);
        }
        if (curNode == null) {
            // 真正执行是不会走到这个逻辑
            RBNode sonNode = node;
            curNode = getParent(sonNode);
            while (getRight(curNode) != sonNode) {
                sonNode = curNode;
                curNode = getParent(sonNode);
            }
        }
        return curNode;
    }

    public void put(K key, V value) {
        // 1. 先按二叉搜索树方式插入
        if (root == null) {
            root = new RBNode(key, value, BLACK);
            return;
        }
        int cmp = 0;
        RBNode point = root;
        RBNode pNode = point;
        while (point != null) {
            pNode = point;
            cmp = key.compareTo(point.key);
            if (cmp < 0) {
                point = pNode.left;
            } else if (cmp == 0) {
                point.setValue(value);
                return;
            } else {
                point = point.right;
            }
        }
        RBNode newNode = new RBNode(key, value, RED);
        if (cmp > 0) {
            setRight(pNode, newNode);
        } else {
            setLeft(pNode, newNode);
        }
        setParent(newNode, pNode);
        // 2. 再进行相应调整
        adjustAfterPut(newNode);
    }

    /**
     * <p>插入之后的调整，存在多种可能性，如何才能写全，需要通过234树进行类比解决</p>
     * <p>插入之后主要有下面几种情况</p>
     * <ul>
     *     <li>情况1：如果插入的是2节点，直接插入</li>
     *     <li>情况2：如果插入的是3节点，如果插入是在两边直接插入，如果是一边那么需要分几种情况讨论</li>
     *     <ul>
     *         <li>1. 如果父亲是祖父的左儿子，当前插入的也是左儿子，形成左3，旋转加变色，变成标准4节点</li>
     *         <li>2. 如果父亲是祖父的左儿子，当前插入的是右儿子</li>
     *         <li>3. 如果父亲是祖父的右儿子，当前插入的也是右儿子</li>
     *         <li>4. 如果父亲是祖父的右儿子，当前插入的也是左儿子</li>
     *     </ul>
     *     <li>情况3：如果插入的是4节点，则需要分裂</li>
     * </ul>
     * @param node
     */
    private void adjustAfterPut(RBNode node) {
        // 当当前节点为null或者是root或者父节点是黑色，都不需要调整
        while (node != null && node != root && getColor(getParent(node)) != BLACK) {
            if (getParent(node) == getLeft(getParent(getParent(node)))) {
                // 父亲是祖父的左儿子
                if (getColor(getRight(getParent(getParent(node)))) == RED) {
                    // 情况四：插入的是4节点，则中间节点需要分裂
                    setColor(getParent(node), BLACK);
                    setColor(getRight(getParent(getParent(node))), BLACK);
                    setColor(getParent(getParent(node)), RED);
                    node = getParent(getParent(node));
                } else {
                    // 情况三：插入的是3节点
                    if (node == getRight(getParent(node))) {
                        node = getParent(node);
                        leftRotate(node);
                    }
                    setColor(getParent(node), BLACK);
                    setColor(getParent(getParent(node)), RED);
                    rightRotate(getParent(getParent(node)));
                }
            } else {
                // 父亲是祖父的右儿子
                if (getColor(getLeft(getParent(getParent(node)))) == RED) {
                    // 情况四：插入的是4节点，则中间节点需要分裂
                    setColor(getParent(node), BLACK);
                    setColor(getLeft(getParent(getParent(node))), BLACK);
                    setColor(getParent(getParent(node)), RED);
                    node = getParent(getParent(node));
                } else {
                    // 情况三：插入的是3节点
                    if (node == getLeft(getParent(node))) {
                        node = getParent(node);
                        rightRotate(node);
                    }
                    setColor(getParent(node), BLACK);
                    setColor(getParent(getParent(node)), RED);
                    leftRotate(getParent(getParent(node)));
                }
            }
        }
        // 调整完之后，不管如何都需要将root的颜色设置为黑色
        root.setColor(BLACK);
    }

    private boolean getColor(RBNode node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(RBNode node, boolean color) {
        if (node != null) {
            node.setColor(color);
        }
    }

    /**
     * 左旋右旋操作是二叉查找树的基本变换操作，不会破坏二叉查找树
     * 左旋操作：红黑树中的操作一定是在有左右儿子的情况下进行
     * @param node 需要进行旋转的节点
     */
    private void leftRotate(RBNode node) {
        if (node != null) {
            // 暂存右儿子
            RBNode rNode = getRight(node);

            // 改变1：右儿子，将右儿子的左儿子设为右儿子
            setRight(node, getLeft(rNode));
            setParent(getLeft(rNode), node);

            // 改变2：右儿子父节点，将父节点设为右儿子的父节点
            transferParent(node, rNode);

            // 改变3：右儿子左儿子，设为当前节点，儿子父亲成对
            setLeft(rNode, node);
            setParent(node, rNode);
        }

    }

    /**
     * 将node的父节点，设为sonNode的父节点
     * @param node 改成此节点的父及诶点
     * @param sonNode 需要改变的节点
     */
    private void transferParent(RBNode node, RBNode sonNode) {
        RBNode pNode = getParent(node);

        if (pNode == null) {
            root = sonNode;
        } else if (getLeft(pNode) == sonNode) {
            setLeft(pNode, sonNode);
        } else {
            setRight(pNode, sonNode);
        }

        setParent(sonNode, pNode);
    }


    /**
     * 左旋右旋操作是二叉查找树的基本变换操作，不会破坏二叉查找树
     * 右旋操作：红黑树中的操作一定是在有左右儿子的情况下进行
     * @param node 需要进行旋转的节点
     */
    private void rightRotate(RBNode node) {
        if (node != null) {
            // 暂存左儿子
            RBNode lNode = getLeft(node);

            // 改变1：左儿子，将左儿子的右儿子设为左儿子
            setLeft(node, getRight(lNode));
            setParent(getRight(lNode), node);

            // 改变2：左儿子父节点，将父节点设为左儿子的父节点
            transferParent(node, lNode);

            // 改变3：左儿子右儿子，设为当前节点
            setRight(lNode, node);
            setParent(node, lNode);
        }
    }

    private void setLeft(RBNode node, RBNode left) {
        if (node != null) {
            node.setLeft(left);
        }
    }

    private void setRight(RBNode node, RBNode right) {
        if (node != null) {
            node.setRight(right);
        }
    }

    private RBNode getRight(RBNode node) {
        return node == null ? null : node.getRight();
    }

    private RBNode getLeft(RBNode node) {
        return node == null ? null : node.getLeft();
    }

    private void setParent(RBNode node, RBNode parent) {
        if (node != null) {
            node.setParent(parent);
        }

    }

    private RBNode getParent(RBNode node) {
        return node == null ? null : node.getParent();
    }
}
