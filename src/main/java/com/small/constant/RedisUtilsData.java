package com.small.constant;

import com.small.entity.Dictionary;
import com.small.entity.Sequences;
import com.small.entity.after.AfterAbility;
import com.small.mapper.DictionaryMapper;
import com.small.mapper.SequencesMapper;
import com.small.mapper.after.AfterAbilityMapper;
import com.small.mapper.after.AfterUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 存放如redis的公用数据
 */
@Component
public class RedisUtilsData {
    private static RedisUtilsData redisUtilsData;

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private AfterAbilityMapper afterAbilityMapper;
    @Resource
    private AfterUserMapper afterUserMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private SequencesMapper sequencesMapper;

    public static SequencesMapper getSequences() {
        return redisUtilsData.sequencesMapper;
    }

    @PostConstruct
    public void init() {
        redisUtilsData = this;
    }

    /**
     * 根据用户岗位id来查询对应的用户名称
     * @param accountId 用户岗位id
     * @return 返回用户名称
     */
    public static String getUserName(Long accountId){
        if(accountId != null) {
            String accountIdString = String.valueOf(accountId);
            if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.h5UserNameMap, accountIdString)) {
                return String.valueOf(redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.h5UserNameMap, accountIdString));
            } else {
                String userName = redisUtilsData.afterUserMapper.getUserName(accountId);
                redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5UserNameMap, accountIdString, userName);
                return userName;
            }
        }else{
            return null;
        }
    }

//    /**
//     * 查询产品名称
//     * @param productParameterId
//     * @return
//     */
//    public static String getProductParameterName(Long productParameterId){
//        if(productParameterId != null) {
//            String productParameterString = String.valueOf(productParameterId);
//            if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.h5ProductParameterNameMap, productParameterString)) {
//                return String.valueOf(redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.h5ProductParameterNameMap, productParameterString));
//            } else {
//                BpProductParameter bpProductParameter = redisUtilsData.bpProductParameterMapper.selectByPrimaryKey(productParameterId);
//                if(bpProductParameter!=null) {
//                    redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5ProductParameterNameMap, productParameterString, bpProductParameter.getProductname());
//                    return bpProductParameter.getProductname();
//                }else{
//                    return null;
//                }
//            }
//        }else{
//            return null;
//        }
//    }

    /**
     * 查询数据字典
     *
     * @return
     */
    public static String getDictionaryItemValue(Long dicId){
        if(dicId != null) {
            String dicIdString = String.valueOf(dicId);
            if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.h5DictionaryItemValueMap, dicIdString)) {
                return String.valueOf(redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.h5DictionaryItemValueMap, dicIdString));
            } else {
                Dictionary dictionary = redisUtilsData.dictionaryMapper.selectByPrimaryKey(dicId);
                if(dictionary!=null) {
                    redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5DictionaryItemValueMap, dicIdString, dictionary.getItemvalue());
                    return dictionary.getItemvalue();
                }else{
                    return null;
                }
            }
        }else{
            return null;
        }
    }

    /**
     * 根据数据字典对应的类型id查询出数据字典里面需要的数据，以集合形式返回
     * @param proTypeId 类型id
     * @return
     */
    public static List<Dictionary> getDictionaryItemValueByProTypeId(Long proTypeId){
        if(proTypeId != null) {
            String proTypeIdString = String.valueOf(proTypeId);
            if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.h5DictionaryItemValueProTypeIdMap, proTypeIdString)) {
                return (List<Dictionary>) redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.h5DictionaryItemValueProTypeIdMap, proTypeIdString);
            } else {
                Dictionary dictionary = new Dictionary();
                dictionary.setProtypeid(proTypeId);
                dictionary.setStatus("0");
                List<Dictionary> list = redisUtilsData.dictionaryMapper.select(dictionary);
                if(dictionary!=null) {
                    redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5DictionaryItemValueProTypeIdMap, proTypeIdString, list);
                    return list;
                }else{
                    return null;
                }
            }
        }else{
            return null;
        }
    }


    /**
     * id预创建
     * @param table
     * @param column
     * @return
     */
    public static Integer getId(String table, String column){
        if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.h5SequencesByIdsMap, table)) {
            Integer id = (Integer) redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.h5SequencesByIdsMap, table);
            id = id+1;
            if(id%50!=0){
                redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5SequencesByIdsMap, table, id);
                return id;
            }
        }
        Sequences sequences = new Sequences();
        sequences.setSequenceName(table);
        sequences = redisUtilsData.sequencesMapper.selectOne(sequences);
        Example example = new Example(Sequences.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sequenceName",table);
        Sequences sequencesUpdate = new Sequences();
        //将数据库中的数据取出，并加上一个 ，然后 当前这个需要创建50个字段出来使用
        Integer idKye = sequences.getSequenceNextHiValue();
        if(idKye != null) {
            //将50个放入到
            sequencesUpdate.setSequenceNextHiValue(idKye + 1);
            redisUtilsData.sequencesMapper.updateByExampleSelective(sequencesUpdate, example);
            redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.h5SequencesByIdsMap, table, idKye*50);
            return idKye*50;
        }else{
            throw new NullPointerException("数据表中的对应列不可为空！");
        }
    }

    public static String getAbilityName(Long abilityId){
        if(abilityId != null) {
            String abilityIdString = String.valueOf(abilityId);
            if (redisUtilsData.redisTemplate.opsForHash().hasKey(RedisNameState.yuAbilityNameMap, abilityIdString)) {
                return String.valueOf(redisUtilsData.redisTemplate.opsForHash().get(RedisNameState.yuAbilityNameMap, abilityIdString));
            } else {
                AfterAbility afterAbility = redisUtilsData.afterAbilityMapper.selectByPrimaryKey(abilityId);
                if(afterAbility!=null) {
                    redisUtilsData.redisTemplate.opsForHash().put(RedisNameState.yuAbilityNameMap, abilityIdString, afterAbility.getAbilityName());
                    return afterAbility.getAbilityName();
                }else{
                    return null;
                }
            }
        }else{
            return null;
        }
    }

}
