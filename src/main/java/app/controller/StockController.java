package app.controller;

import app.model.Stock;
import app.repository.StockRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by ka on 06/03/16.
 */
@RestController
public class StockController {

    @Autowired
    protected StockRepository stockRepository;

    @RequestMapping("/")
    public ModelAndView root() {
        return new ModelAndView("react");
    }

    @RequestMapping("/find/{sku}")
    public Stock findById(@PathVariable("sku") String sku) {
        return stockRepository.findOne(sku);
    }

    @RequestMapping("/page/{page}/{size}/{sort}")
    public ModelAndView page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @PathVariable("sort") String sort) {
        Page<Stock> stockPage = stockRepository.findAll(new PageRequest(page-1, size, Sort.Direction.ASC, sort));
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("stockPage", stockPage);
        modelAndView.addObject("page", stockPage);
        modelAndView.addObject("pageSize", size);
        return modelAndView;
    }

    @RequestMapping("/page")
    public Iterable<Stock> findAll() {
        return stockRepository.findAll(new PageRequest(0, 20, Sort.Direction.ASC, "sku"));
    }

    /*
    @RequestMapping(path = {"/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("stockList", findAll());
        return modelAndView;
    }
    */

    @RequestMapping("/load")
    public String load() throws IOException {

        InputStream in = new FileInputStream("/Users/ka/Downloads/stock_report.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        int i = 0;
        while (it.hasNext()) {
            Row row = it.next();
            i++;

            if (i<5) {
                continue;
            }
            /*
            if (i>8) {
                break;
            }
            */

            Stock stock = new Stock();
            Cell name = row.getCell(1);
            //System.out.println("name: " + name.getStringCellValue());
            stock.name = name.getStringCellValue().trim();
            Cell sku = row.getCell(4);
            if (sku.getCellType()==Cell.CELL_TYPE_NUMERIC) {
                System.out.println("sku: " + sku.getNumericCellValue());
                stock.sku = String.valueOf((int) sku.getNumericCellValue()).trim();
            } else if (sku.getCellType()==Cell.CELL_TYPE_STRING) {
                System.out.println("sku: " + sku.getStringCellValue());
                stock.sku = sku.getStringCellValue().trim();
            } else {
                System.out.println("sku: ?" + sku.getCellType());
            }
            if (stock.sku==null) {
                continue;
            }
            Cell amount = row.getCell(9);
            //System.out.println("amount: " + amount.getNumericCellValue());
            stock.amount = (int) amount.getNumericCellValue();

            stockRepository.save(stock);

            /*
            Iterator<Cell> ci = row.cellIterator();
            while (ci.hasNext()) {
                Cell next = ci.next();
                switch (next.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(next.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.println(next.getNumericCellValue());
                        break;
                }
            }
            */
        }


        return "OK";
    }
}
