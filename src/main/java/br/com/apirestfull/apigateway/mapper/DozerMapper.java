package br.com.apirestfull.apigateway.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O,D>D parseObject(O origin, Class<D> destiny){
        return mapper.map(origin,destiny);
    }
    public static <O,D> List<D> parseListObjects(List<O> origin, Class<D> destiny){
        List<D> destinyObjects = new ArrayList<D>();
        origin.forEach(o -> destinyObjects.add(mapper.map(o,destiny)));
        return destinyObjects;
    }
}
