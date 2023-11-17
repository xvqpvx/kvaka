package caterpillar.kvaka.graphql;

import caterpillar.kvaka.entity.InventorsInventions;
import caterpillar.kvaka.service.impl.InventorsInventionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class InventorsInventionsQuery {

    private final InventorsInventionsServiceImpl recordsService;

    @Autowired
    public InventorsInventionsQuery(InventorsInventionsServiceImpl recordsService) {
        this.recordsService = recordsService;
    }

    @QueryMapping(name = "inventorsInventions")
    public InventorsInventionsConnection inventorsInventions(@Argument Integer first,
                                                             @Argument String after, @Argument String orderBy) {
        // получение записей из базы данных с использованием параметров пагинации и сортировки
        List<InventorsInventions> edges = recordsService.findAll(0, first != null ? first : 10, orderBy, Sort.Direction.ASC);

        // создание списка элементов и курсоров
        List<InventorsInventionsEdge> edgesList = new ArrayList<>();
        List<String> cursors = new ArrayList<>();
        int index = 0;
        for (InventorsInventions record : edges) {
            String cursor = Base64.getEncoder().encodeToString(String.valueOf(index++).getBytes());
            edgesList.add(new InventorsInventionsEdge(record, cursor));
            cursors.add(cursor);
        }

        // создание объекта CustomPageInfo с информацией о странице
        CustomPageInfo pageInfo = new CustomPageInfo(
                cursors.get(0),
                cursors.get(cursors.size() - 1),
                cursors.size() > 1,
                cursors.size() > 1 ? cursors.get(cursors.size() - 2) : null
        );

        // создание объекта InventorsInventionsConnection, содержащего список элементов и объект CustomPageInfo
        return new InventorsInventionsConnection(edgesList, pageInfo);
    }
}