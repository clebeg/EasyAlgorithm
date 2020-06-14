package io.github.clebeg.model;

import java.util.Arrays;

/**
 * 并查集：
 *  假设有N个帮派，每个帮派有很多成员，现在有几个问题，需要技术人员快速实现
 *      1. 给一个成员快速找到他的帮主
 *      2. 快速查找两个成员是不是属于一个帮派
 *      3. 如何快速合并两个帮派
 * 有几种实现的方案：
 * 方案1（QuickFind）
 *      合并的时候，将当前成员所在帮派的所有成员 成为 另一个成员所在帮派老大的直接小弟 （这样：所有的人都属于老大直接管）
 *      查找的时候，直接找到自己的父节点就行
 * 方案2 (QuickUnion)
 *      合并的时候，将当前成员所在帮派的老大 成为 另一个成员所在帮派老大的直接小弟 （这样：所有的人还是属于自己老大管）
 *      查找的时候，需要一层一层往上找，层级太深，复杂度越高
 *  优化思路：
 *      合并的时候保证树高度不要太高 （rank、size）
 *      查找的时候顺便将层级降低 （路径压缩、分裂、择半
 *  这里选择基于 方案二、记录rank、进行择半的优化）
 */
public class UnionFind {
    private int[] parents;
    private int[] ranks;

    public int[] getParents() {
        return parents;
    }

    public int[] getRanks() {
        return ranks;
    }

    /**
     * 记录成员总数量，所有集合的成员之和
     * @param numbers
     */
    public UnionFind(int numbers) {
        // 每个成员的父节点都初始化为自己
        this.parents = new int[numbers];
        for (int i = 0; i < parents.length; i++) {
            this.parents[i] = i;
        }
        this.ranks = new int[numbers];
        // 每一颗树的高度都是1
        for (int i = 0; i < ranks.length; i++) {
            this.ranks[i] = 1;
        }
    }

    /**
     * 找到自己所在的集合的根节点（帮派老大）
     * @param v 成员编号
     * @return
     */
    public int find(int v) {
        int cur = v;
        while (cur != parents[cur]) {
            // 路径择半 自己的父亲 = 自己的父亲的父亲
            parents[cur] = parents[parents[cur]];
            cur = parents[cur];
        }
        return cur;
    }

    /**
     * 判断两个成员是不是同一个集合
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        return p1 == p2;
    }

    /**
     * 将两个成员所在的集合合并为一个集合
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2] += 1;
        }
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(12);
        unionFind.union(0, 1);
        unionFind.union(0, 2);
        unionFind.union(0, 3);
        unionFind.union(4, 5);
        unionFind.union(4, 6);
        unionFind.union(1, 4);
        System.out.println(Arrays.toString(unionFind.getParents()));
        System.out.println(Arrays.toString(unionFind.getRanks()));
        System.out.println(unionFind.isSame(2, 6));
        System.out.println(Arrays.toString(unionFind.getParents()));
        System.out.println(Arrays.toString(unionFind.getRanks()));
    }
}
