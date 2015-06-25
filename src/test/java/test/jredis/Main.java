package test.jredis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

public class Main {
	private static final int DEFAULT_TIMEOUT = 120000;
	private static JedisPool JedisPool=null;
	
	static{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setTestOnBorrow(true);
		config.setMaxTotal(100);
		config.setMaxIdle(50);
		config.setMaxWaitMillis(10000);
		JedisPool = new JedisPool(config,"localhost", 6379, DEFAULT_TIMEOUT);
	}
	
	
	public void testTrans(){
		Jedis jedis=Main.getJedis();
		
		for (int i = 0; i < 100000; i++) {
	        String result = jedis.set("n" + i, "n" + i);
	    }
		jedis.flushDB();
		
		
		Transaction tx = jedis.multi();
		for (int i = 0; i < 100000; i++) {
	        tx.set("t" + i, "t" + i);
	    }
	    List<Object> results = tx.exec();
	    jedis.disconnect();
	}
	
	
	public static Jedis getJedis(){
		
//		return new Jedis("127.0.0.1", 6379);
		return JedisPool.getResource();
		
	}
	
	
	
	
	public void testString(){
		Jedis jedis=Main.getJedis();
		jedis.set("name","xinxin");
		System.out.println(jedis.get("name"));
		jedis.append("name", " is my lover"); //拼接
		System.out.println(jedis.get("name")); 
		System.out.println(jedis.get("name"));
		jedis.mset("name","liuling","age","23","qq","476777389");
		jedis.incr("age"); //进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
		
		// 若key不存在，则存储  
        jedis.setnx("foo", "foo not exits");  
        jedis.setnx("foo", "exits");  
        // 设置key的有效期，并存储数据  
        jedis.setex("foo", 2, "foo not exits");  
        // 获取并更改数据  
        jedis.getSet("foo", "foo modify");
        System.out.println(jedis.get("foo")); 
        
        
	}
	
	/*
	 * 相关方法以l打头（list）
	 */
	public void testList(){
		Jedis jedis=Main.getJedis();
        
        
        jedis.rpush("messages", "Hello how are you?");  
        jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");  
        jedis.rpush("messages", "I should look into this NOSQL thing ASAP");  

        // 再取出所有数据jedis.lrange是按范围取出，  
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
        List<String> values = jedis.lrange("messages", 0, -1);  
        System.out.println(values);  
     // 添加数据  
        jedis.lpush("lists", "vector");  
        jedis.lpush("lists", "ArrayList");  
        jedis.lpush("lists", "LinkedList");  
        // 数组长度  
        System.out.println(jedis.llen("lists"));  
        // 排序  
//        System.out.println(jedis.sort("lists"));  
        // 字串  
        System.out.println(jedis.lrange("lists", 0, 3));  
        // 修改列表中单个值  
        jedis.lset("lists", 0, "hello list!");  
        // 获取列表指定下标的值  
        System.out.println(jedis.lindex("lists", 1));  
        // 删除列表指定下标的值  
        System.out.println(jedis.lrem("lists", 1, "vector"));  
        // 删除区间以外的数据  
        System.out.println(jedis.ltrim("lists", 0, 1));  
        // 列表出栈  
        System.out.println(jedis.lpop("lists"));  
        // 整个列表值  
        System.out.println(jedis.lrange("lists", 0, -1));  
        
        
	}
	
	/**
	 * 相关方法以s打头(set)
	 */
	public void testSet(){
		Jedis jedis=Main.getJedis();
		// 添加数据  
        jedis.sadd("sets", "HashSet");  
        jedis.sadd("sets", "SortedSet");  
        jedis.sadd("sets", "TreeSet");  
        // 判断value是否在列表中  
        System.out.println(jedis.sismember("sets", "TreeSet"));  
        ;  
        // 整个列表值  
        System.out.println(jedis.smembers("sets"));  
        // 删除指定元素  
        System.out.println(jedis.srem("sets", "SortedSet"));  
        // 出栈  
        System.out.println(jedis.spop("sets"));  
        System.out.println(jedis.smembers("sets"));  
        //  
        jedis.sadd("sets1", "HashSet1");  
        jedis.sadd("sets1", "SortedSet1");  
        jedis.sadd("sets1", "TreeSet");  
        jedis.sadd("sets2", "HashSet2");  
        jedis.sadd("sets2", "SortedSet1");  
        jedis.sadd("sets2", "TreeSet1");  
        // 交集  
        System.out.println(jedis.sinter("sets1", "sets2"));  
        // 并集  
        System.out.println(jedis.sunion("sets1", "sets2"));  
        // 差集  
        System.out.println(jedis.sdiff("sets1", "sets2"));  
        
	}
	
	/**
	 * 相关方法姨h打头（hash）
	 */
	public void testHashMap(){
		Jedis jedis=Main.getJedis();
		jedis.hset("website", "google", "www.google.cn");
        jedis.hset("website", "baidu", "www.baidu.com");
        jedis.hset("website", "sina", "www.sina.com");
        jedis.hdel("website", "google");
	}
	
	
	
	public static void main(String args[]){
		Jedis jedis=Main.getJedis();
		Main main=new Main();
		main.testList();
		// keys中传入的可以用通配符  
        System.out.println(jedis.keys("*")); // 返回当前库中所有的key 
        
        main.testTrans();
        
        
        
        // 清空数据  
        System.out.println(jedis.flushDB()); 
        
	}

}
