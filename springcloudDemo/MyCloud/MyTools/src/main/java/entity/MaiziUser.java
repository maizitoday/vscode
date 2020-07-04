package entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author yubo
 * @version V2.0
 * @Description:
 * @date 2019/4/17 下午6:07
 * @Company: cecsm.com
 * @Copyright Copyright (c) 2017
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MaiziUser implements Serializable{
	private String name;
	private String sex;
	private int age;
}
