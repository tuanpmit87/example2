package metro.example2.controller;

import metro.example2.common.ApiCustomError;
import metro.example2.dao.PostDao;
import metro.example2.dao.PrefectureDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post_offices")
public class RestApiController {
    @Autowired
    private PostDao postDao;

    @Autowired
    private PrefectureDao prefDao;

    @RequestMapping(value = "/post/{postcode}", method = RequestMethod.GET)
    public ResponseEntity<?> getPostInfo(@PathVariable("postcode") String postcode) {
        postcode = postcode.replaceAll("[ /-]+","");
        if (postcode.equals("") || !postcode.matches("\\d+")) {
            return new ResponseEntity(
                new ApiCustomError("Giá trị không hợp lệ", "post code sai định dạng"),
                HttpStatus.BAD_REQUEST
            );
        }

        List<Map<String, Object>> postList = postDao.findPostByPostcode(postcode);
        if (postList.size() == 0) {
            return new ResponseEntity(
                new ApiCustomError("Cố gắng thao tác một tài nguyên không tồn tại.", "Không có kết quả"),
                HttpStatus.NOT_FOUND
            );
        }
        
        Map<String, Object> response = new HashMap<>();;
        response.put("data", postList);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/prefectures/{prefCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrefInfo(@PathVariable("prefCode") String prefCode) {
        prefCode = prefCode.replaceAll("[ /-]+","");
        if (prefCode.equals("") || !prefCode.matches("\\d+")) {
            return new ResponseEntity(
                    new ApiCustomError("Giá trị không hợp lệ", "prefecture code sai định dạng"),
                    HttpStatus.BAD_REQUEST
            );
        }

        List<Map<String, Object>> prefList = prefDao.findPrefByPrefCode(prefCode);
        if (prefList.size() == 0) {
            return new ResponseEntity(
                new ApiCustomError("Cố gắng thao tác một tài nguyên không tồn tại.", "Không có kết quả"),
                HttpStatus.NOT_FOUND
            );
        }

        Map<String, Object> response = new HashMap<>();;
        response.put("data", prefList);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
