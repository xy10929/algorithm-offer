package class36;
//lc815
import java.util.ArrayList;
import java.util.HashMap;

// 来自三七互娱
// Leetcode原题 : https://leetcode.com/problems/bus-routes/
public class Code11_BusRoutes {
//	public int numBusesToDestination(int[][] routes, int source, int target) {
//		if (source == target) {
//			return 0;
//		}
//		int n = routes.length;//线路数
//		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//k-站  v-该站在哪些线路中
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < routes[i].length; j++) {//遍历站点
//				if (!map.containsKey(routes[i][j])) {
//					map.put(routes[i][j], new ArrayList<>());
//				}
//				map.get(routes[i][j]).add(i);//该站点在下标为i的线路中
//			}
//		}
//		ArrayList<Integer> queue = new ArrayList<>();//宽度优先的队列 存放站点下标
//		boolean[] set = new boolean[n];//记录线路是否已经加入过队列
//		for (int route : map.get(source)) {//从map中得到起始站点所在的所有线路
//			queue.add(route);//把所在的每条线路加入队列
//			set[route] = true;
//		}
//		int ans = 1;//已经经过的线路数
//		while (!queue.isEmpty()) {//队列非空时 还没有推出所有可达的线路
//			ArrayList<Integer> next = new ArrayList<>();//向外层推 得到下一层可达的线路 存放进next
//			for (int route : queue) {//队列中的线路下标为route
//				int[] stations = routes[route];//当前线路的可达的站点的数组
//				for (int station : stations) {
//					if (station == target) {//如果可达target  直接返回线路数
//						return ans;
//					}
//					for (int nextroute : map.get(station)) {//根据map  由站点得到下一步可达的线路
//						if (!set[nextroute]) {//只有 没有进入过的队列的线路-还未到达的线路 才加入队列
//							next.add(nextroute);
//							set[nextroute] = true;
//						}
//					}
//				}
//			}
//			queue = next;//用得到的下一步的队列替换queue
//			ans++;//层数 即路线数增加
//		}
//		return -1;//target不可达
//	}
	// 0 : [1,3,7,0]
	// 1 : [7,9,6,2]
	// ....
	// 返回：返回换乘几次+1 -> 返回一共坐了多少条线的公交。
	public static int numBusesToDestination(int[][] routes, int source, int target) {
		if (source == target) {
			return 0;
		}
		int n = routes.length;
		// key : 车站
		// value : list -> 该车站拥有哪些线路！
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < routes[i].length; j++) {
				if (!map.containsKey(routes[i][j])) {
					map.put(routes[i][j], new ArrayList<>());
				}
				map.get(routes[i][j]).add(i);
			}
		}
		ArrayList<Integer> queue = new ArrayList<>();
		boolean[] set = new boolean[n];
		for (int route : map.get(source)) {
			queue.add(route);
			set[route] = true;
		}
		int len = 1;
		while (!queue.isEmpty()) {
			ArrayList<Integer> nextLevel = new ArrayList<>();
			for (int route : queue) {
				int[] bus = routes[route];
				for (int station : bus) {
					if (station == target) {
						return len;
					}
					for (int nextRoute : map.get(station)) {
						if (!set[nextRoute]) {
							nextLevel.add(nextRoute);
							set[nextRoute] = true;
						}
					}
				}
			}
			queue = nextLevel;
			len++;
		}
		return -1;
	}

}