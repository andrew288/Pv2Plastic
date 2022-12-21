package com.example.myfirstapplication.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Category;
import com.example.myfirstapplication.db.Product;
import com.example.myfirstapplication.db.converters.ConverterBitMap;
import com.example.myfirstapplication.db.converters.ConverterDate;
import com.example.myfirstapplication.db.daos.DaoCategory;
import com.example.myfirstapplication.db.daos.DaoProduct;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AwardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AwardsFragment extends Fragment {

    Button btAddProduct;
    TextView tvShowProducts;
    ImageView imageProduct;

    public AwardsFragment() {
        // Required empty public constructor
    }


    public static AwardsFragment newInstance() {
        AwardsFragment fragment = new AwardsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_awards, container, false);
        btAddProduct = root.findViewById(R.id.awards_button_category_add);
        tvShowProducts = root.findViewById(R.id.awards_info_category);
        imageProduct = root.findViewById(R.id.awards_info_image);

        btAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                insertCategories();
//                insertProduct();
            }
        });
        return root;
    }

    private void insertProduct(){
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoProduct productDao = db.daoProduct();
        productDao.insertProduct(
                new Product("Botella de plástico de bebida",
                        "Vacía restos de líquido. Remueve la tapa y la etiqueta. Finalmente aplastarla para que ocupe menos espacio",
                        1, "mililitros", 250, 5, 500, 8, 750, 12,
                        1000, 17,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.botella_plastico_para_beber)))
        );
        productDao.insertProduct(
                new Product("Envase de aceite",
                        "Vacía restos de líquido. Remueve la tapa y la etiqueta. Finalmente aplastarla para que ocupe menos espacio",
                        1, "mililitros", 100, 5, 500, 9, 900, 14, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.botella_aceite)))
        );
        productDao.insertProduct(
                new Product("Frascos de plástico",
                        "Vacía restos de líquido. Remueve la tapa y la etiqueta. Finalmente aplastarla para que ocupe menos espacio."
                        , 1, "mililitros", 250, 5, 500, 9, 1000, 12, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.frasco_plastico)))
        );
        productDao.insertProduct(
                new Product("Envase de jarabe",
                        "Lavar muy bien el envase con agua. Remover tapas, etiquetas y finalmente aplastarla para que ocupe menos espacio",
                        1, "mililitros", 60, 2, 125, 3, 0, 0, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.botella_jarabe)))
        );
        productDao.insertProduct(
                new Product("Recipiente de producto de limpieza",
                        "Lavar muy bien el envase con agua. Remover tapas, etiquetas y posibles chisquetes incorporados. Finalmente aplastarla para que ocupe menos espacio",
                        2, "mililitros", 300, 6, 1000, 15, 2000, 25, 3500, 40,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.producto_limpieza)))
        );
        productDao.insertProduct(
                new Product("Paquete de leche",
                        "Lavar el envase, extraer todas las etiquetas y depositar en el contenedor amarillom; estos envases requieren de herramientas especializadas para su reciclaje",
                        2, "mililitros", 400,15, 750, 22, 1000, 27, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.paquete_leche)))
        );
        productDao.insertProduct(
                new Product("Frascos cosméticos",
                        "Remover todas las etiquetas y tapas del envase, si es posible aplastarlas",
                        2, "mililitros", 20, 3, 30, 4, 70, 5.5f, 120, 8,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.frasco_cosmetico)))
        );
        productDao.insertProduct(
                new Product("Bolsas de basura",
                        "Las bolsas de plástico no deben tener elementos en su interior. Además es difícil de reciclar",
                        3, "centimetros", 15, 2, 30, 2.5f, 45, 3, 80, 4,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bolsa_basura)))
        );
        productDao.insertProduct(
                new Product("Papel de cocina transparente",
                        "Se debe retirar los restos orgánicos antes de reciclar",
                        3, "centimetros", 15, 3, 30, 4, 50, 6, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.papel_cocina_transparente)))
        );
        productDao.insertProduct(
                new Product("Bolsas para congelar",
                        "Las bolsas para congelar son difíciles de reutilizar, se recomienda el envío a centros de acopio",
                        3, "centimetros", 14, 3, 24, 4, 35, 7, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bolsa_para_congelar)))
        );
        productDao.insertProduct(
                new Product("Bolsas de plástico",
                        "Las bolsas de plástico son difíciles de reutilizar, se recomienda el envío a centros de acopio",
                        3, "centimetros", 15, 3, 22, 4, 32, 7, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.bolsa_plastico)))
        );
        productDao.insertProduct(
                new Product("Pajitas para beber",
                        "Reemplazarlas por objetos reutilizables como sorbetes de silicona",
                        4, "centimetros", 6, 1, 8, 2, 12, 3, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.pajita_de_beber)))

        );
        productDao.insertProduct(
                new Product("Envases de alimentos reutilizables",
                        "Estos objetos de plástico se pueden reutilizar para almacenar alimentos o comidas",
                        4, "mililitros", 200, 12, 400, 16, 600, 20, 800, 24,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.taper_alimentos)))
        );
        productDao.insertProduct(
                new Product("Utensilios de cocina",
                        "Preferible no pedir utensilios de plásticos, se deben usar utensilios de metal en la medida de lo posible.",
                        4, "gramos", 3, 3, 4, 3.8f, 0, 0, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.utensilios_cocina)))
        );
        productDao.insertProduct(
                new Product("Vaso de helado",
                        "Lavar muy bien el envase con agua y aplastarlo para que ocupe menos espacio",
                        5, "mililitros", 150, 4, 250, 8, 350, 12, 500, 16,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.vaso_helado)))
        );
        productDao.insertProduct(
                new Product("Contenedor de huevos",
                        "Aplastar el plástico para que ocupe menos espacio",
                        5, "gramos", 6, 6, 12, 12, 15, 15, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.contenedor_huevos)))
        );
        productDao.insertProduct(
                new Product("Estuche de CD o DVD",
                        "Lavar muy bien el estuche con agua y reutilizar",
                        5, "centimetros", 10, 20, 25, 40, 0, 0, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.estuche_cd_dvd)))
        );
        productDao.insertProduct(
                new Product("Envase de alimentos",
                        "Lavar muy bien el envase con agua y aplastarlo para que ocupe menos espacio",
                        5, "centimetros", 15, 5, 18, 8, 22, 12, 26, 15,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.envase_alimentos)))
        );
        productDao.insertProduct(
                new Product("Botella de salsa",
                        "Lavar muy bien el envase y separar la tapa del envase",
                        6, "mililitros", 240, 10, 480, 18, 950, 34, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.botella_salsa)))
        );
        productDao.insertProduct(
                new Product("Jeringa",
                        "Lavar muy bien y desinfectar las jeringuillas antes de reutilizar",
                        6, "mililitros", 2, 3, 5, 5.5f, 10, 7, 20, 9,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.jeringa)))
        );
        productDao.insertProduct(
                new Product("Biberón",
                        "Lavar muy bien con agua el envase",
                        6, "mililitros", 60, 77, 120, 125, 250, 248, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.biberon)))
        );
        productDao.insertProduct(
                new Product("Recipiente de embutido",
                        "Lavar muy bien con agua el envase para reutilizar",
                        6, "mililitros", 190, 10, 250, 14, 390, 20, 0, 0,
                        ConverterBitMap.getStringFromBitMap(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.recipiente_embutido)))
        );
        Log.d("ADD PRODUCTS", "END PROCESS");

    }
    private void insertCategories(){
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoCategory categoryDao = db.daoCategory();
        categoryDao.insertCategory(new Category("PET"));
        categoryDao.insertCategory(new Category("PEAD"));
        categoryDao.insertCategory(new Category("LDPE"));
        categoryDao.insertCategory(new Category("PP"));
        categoryDao.insertCategory(new Category("PS"));
        categoryDao.insertCategory(new Category("Otros"));


    }
}