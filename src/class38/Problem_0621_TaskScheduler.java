package class38;

public class Problem_0621_TaskScheduler {
//  public int leastInterval(char[] tasks, int n) {
//  int[] count = new int[128];
//  int maxCount = 0;
//  for(char c : tasks){
//      count[c]++;
//      maxCount = Math.max(maxCount, count[c]);//出现最多的次数
//  }
//  int kinds = 0;
//  for(int c : count){
//      if(c == maxCount){
//          kinds++;//出现最多次的有几种
//      }
//  }
//  int groups = (n + 1) * (maxCount - 1);//每组以出现次数最多的1/几种任务开头 每组长度为n+1(保证间隔)  最后一组后面不设置间隔 所以组数为maxCount-1  所以groups表示理论上(剩下的任务未填满空格 不溢出的情况下)除了最后一组之外 前面所有组的总长度
//  int hasCooldown = groups + kinds;//最后一组只有开头的kinds个出现次数最多的任务 所以hasCooldown为不溢出的情况的总时间
//  return Math.max(tasks.length, hasCooldown);//如果溢出 则没有任何间隔 总时间即为总任务数
//}
	// ['A', 'B', 'A']
	public static int leastInterval(char[] tasks, int free) {
		int[] count = new int[256];
		// 出现最多次的任务，到底是出现了几次
		int maxCount = 0;
		for (char task : tasks) {
			count[task]++;
			maxCount = Math.max(maxCount, count[task]);
		}
		// 有多少种任务，都出现最多次
		int maxKinds = 0;
		for (int task = 0; task < 256; task++) {
			if (count[task] == maxCount) {
				maxKinds++;
			}
		}
		// maxKinds : 有多少种任务，都出现最多次
		// maxCount : 最多次，是几次？
		// 砍掉最后一组剩余的任务数
		int tasksExceptFinalTeam = tasks.length - maxKinds;
		int spaces = (free + 1) * (maxCount - 1);
		// 到底几个空格最终会留下！
		int restSpaces = Math.max(0, spaces - tasksExceptFinalTeam);
		return tasks.length + restSpaces;
		// return Math.max(tasks.length, ((n + 1) * (maxCount - 1) + maxKinds));
	}
	

}