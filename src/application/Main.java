package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		AVL<Department> departments = new AVL<>();

		File deps = new File("departments.txt");
		if (deps.exists()) {
			Scanner in = new Scanner(deps);
			while (in.hasNext()) {
				String[] tkz = in.nextLine().trim().split("/");
				Department dept = new Department(tkz[0].trim(), tkz[1].trim());
				departments.insert(dept);
				File sts = new File(tkz[1].trim());
				if (sts.exists()) {
					Scanner in2 = new Scanner(sts);
					while (in2.hasNext()) {
						String[] tkz2 = in2.nextLine().trim().split("/");
						dept.students.insert(
								new Student(tkz2[0].trim(), Integer.parseInt(tkz2[1].trim()), Double.parseDouble(tkz2[2].trim()), tkz2[3].trim()));
					}
					in2.close();
				}
			}
			in.close();
		}

		BorderPane bp = new BorderPane();
		Scene scene1 = new Scene(bp, 400, 250);
		Label l = new Label("Birzeit University");
		l.setAlignment(Pos.CENTER);
		l.setFont(new Font(30));
		Button b1 = new Button("Departments");
		b1.setPrefSize(120, 20);
		b1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");
		b1.setOnAction(e -> {
			BorderPane bp2 = new BorderPane();
			Scene scene2 = new Scene(bp2, 400, 250);

			Label l2 = new Label("Departments");
			l2.setFont(new Font(30));
			Button db1 = new Button("Print out departments");
			db1.setPrefSize(150, 10);
			db1.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			db1.setOnAction(s -> {
				Label l3 = new Label("Departments Printed");
				l3.setFont(new Font(30));
				l3.setAlignment(Pos.CENTER);
				TextField t = new TextField();
				t.setPrefSize(180, 130);
				t.setAlignment(Pos.CENTER);
				t.setText(departments.toStringInorder());
				Button b = new Button("Back");
				Button h = new Button("Print Hieght");
				h.setOnAction(r -> {
					t.appendText("\n\t           the tree hieght is: " + departments.height());
				});
				h.setPrefSize(150, 10);
				h.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");
				b.setPrefSize(150, 10);
				b.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");
				b.setOnAction(f -> {
					stage.setScene(scene2);
				});

				HBox hb = new HBox();
				hb.getChildren().addAll(b, h);
				hb.setSpacing(10);
				hb.setAlignment(Pos.CENTER);

				BorderPane bp3 = new BorderPane();
				bp3.setTop(l3);
				bp3.setAlignment(l3, Pos.CENTER);
				bp3.setCenter(t);
				// bp3.setAlignment(t, Pos.CENTER);
				bp3.setBottom(hb);
				bp3.setAlignment(hb, Pos.CENTER);

				Scene scene3 = new Scene(bp3, 400, 250);
				stage.setScene(scene3);
				stage.show();
			});

			Button db2 = new Button("Serch for department");
			db2.setPrefSize(150, 10);
			db2.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");
			db2.setOnAction(x -> {
				Label la = new Label("Serhcing for a department");
				la.setFont(new Font(30));

				Label la2 = new Label("Enter Department Name:");
				la2.setFont(new Font(15));
				la2.setAlignment(Pos.CENTER);

				TextArea t2 = new TextArea();
				t2.setPrefSize(100, 100);
				TextArea t = new TextArea();
				t.setPrefSize(100, 10);
				GridPane g = new GridPane();
				g.addRow(0, la2, t);
				g.setHgap(10);

				VBox v2 = new VBox();
				v2.getChildren().addAll(g, t2);
				v2.setSpacing(10);

				Button bs1 = new Button("Serch");
				bs1.setPrefSize(100, 20);
				bs1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				bs1.setOnAction(f -> {
					Department d = new Department(t.getText());
					TNode<Department> de = new TNode(departments.find(d));
					t2.setText(de.toString());

				});
				Button bs2 = new Button("Back");
				bs2.setPrefSize(100, 20);
				bs2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				bs2.setOnAction(r -> {
					stage.setScene(scene2);
				});
				HBox h = new HBox();
				h.getChildren().addAll(bs2, bs1);
				h.setSpacing(15);
				h.setAlignment(Pos.CENTER);

				BorderPane b = new BorderPane();
				b.setTop(la);
				b.setAlignment(la, Pos.CENTER);
				b.setCenter(v2);
				b.setBottom(h);

				Scene scene4 = new Scene(b, 400, 250);
				stage.setScene(scene4);
				stage.show();

			});

			Button db3 = new Button("Insert new department\n\n\n");
			db3.setPrefSize(150, 10);
			db3.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			db3.setOnAction(x -> {
				Label ins = new Label("Inserting a Department");
				ins.setFont(new Font(30));

				Label ll1 = new Label("Enter Department Name");
				ll1.setFont(new Font(15));
				Label ll2 = new Label("Enter Department Students file name");
				ll2.setFont(new Font(15));

				TextArea t1 = new TextArea();
				t1.setPrefSize(120, 10);
				TextArea t2 = new TextArea();
				t2.setPrefSize(120, 10);

				GridPane gp = new GridPane();
				gp.addRow(0, ll1, t1);
				gp.addRow(1, ll2, t2);
				gp.setVgap(5);
				gp.setHgap(5);

				Button b = new Button("ADD");
				b.setPrefSize(100, 20);
				b.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");

				b.setOnAction(c -> {
					departments.insert(new Department(t1.getText(), t2.getText()));
					FileWriter out;
					try {
						out = new FileWriter(new File("departments.txt"), true);
						PrintWriter write = new PrintWriter(out);
						write.println(t1.getText() + "/" + t2.getText());
						write.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					t1.clear();
					t2.clear();
				});

				Button b2 = new Button("Back");
				b2.setPrefSize(100, 20);
				b2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				b2.setOnAction(w -> {
					stage.setScene(scene2);
				});

				HBox h = new HBox();
				h.getChildren().addAll(b2, b);
				h.setSpacing(10);
				h.setAlignment(Pos.CENTER);

				BorderPane bo = new BorderPane();
				bo.setTop(ins);
				bo.setAlignment(ins, Pos.CENTER);
				bo.setCenter(gp);
				bo.setAlignment(gp, Pos.CENTER);
				bo.setBottom(h);
				bo.setAlignment(h, Pos.CENTER);

				Scene scene5 = new Scene(bo, 400, 250);
				stage.setScene(scene5);
				stage.show();

			});

			Button db4 = new Button("Delete a department");
			db4.setPrefSize(150, 10);
			db4.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			db4.setOnAction(b -> {
				Label la = new Label("Deleting a department");
				la.setFont(new Font(30));

				Label la2 = new Label("Enter Department Name:");
				la2.setFont(new Font(15));
				la2.setAlignment(Pos.CENTER);

				TextArea t = new TextArea();
				t.setPrefSize(100, 10);
				GridPane g = new GridPane();
				g.addRow(0, la2, t);
				g.setHgap(10);

				Button bs1 = new Button("Delete");
				bs1.setPrefSize(100, 20);
				bs1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				bs1.setOnAction(f -> {
					Department d = new Department(t.getText());
					TNode<Department> de = new TNode(departments.find(d));
					departments.delete(de.data);

					remove("departments.txt", t.getText());
					t.clear();

				});
				Button bs2 = new Button("Back");
				bs2.setPrefSize(100, 20);
				bs2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				bs2.setOnAction(r -> {
					stage.setScene(scene2);
				});
				HBox h = new HBox();
				h.getChildren().addAll(bs2, bs1);
				h.setSpacing(15);
				h.setAlignment(Pos.CENTER);

				BorderPane b2 = new BorderPane();
				b2.setTop(la);
				b2.setAlignment(la, Pos.CENTER);
				b2.setCenter(g);
				b2.setBottom(h);
				b2.setAlignment(g, Pos.CENTER);

				Scene scene6 = new Scene(b2, 400, 250);
				stage.setScene(scene6);
				stage.show();
			});

			Button db5 = new Button("Back");
			db5.setPrefSize(150, 10);
			db5.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");
			db5.setAlignment(Pos.CENTER);
			db5.setOnAction(a -> {
				stage.setScene(scene1);
			});

			GridPane gp = new GridPane();
			gp.addColumn(0, db1, db2);
			gp.addColumn(1, db3, db4);
			gp.setHgap(5);
			gp.setVgap(5);
			gp.setAlignment(Pos.CENTER);

			bp2.setTop(l2);
			bp2.setAlignment(l2, Pos.CENTER);
			bp2.setCenter(gp);
			bp2.setBottom(db5);
			bp2.setAlignment(db5, Pos.CENTER);

			stage.setScene(scene2);
			stage.show();

		});
		Button b2 = new Button("Students");
		b2.setPrefSize(120, 20);
		b2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");

		b2.setOnAction(e -> {
			BorderPane bp2 = new BorderPane();
			Scene scene7 = new Scene(bp2, 400, 250);

			Label label = new Label("Strudents");
			label.setFont(new Font(30));

			Button button1 = new Button("Print Out Hash Table");
			button1.setPrefSize(150, 10);
			button1.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			button1.setOnAction(z -> {
				Label ll = new Label("Printing Hash Table");
				ll.setFont(new Font(30));

				Label ll2 = new Label("Enter Department Name");
				ll2.setFont(new Font(20));

				TextArea ta1 = new TextArea();
				ta1.setPrefSize(120, 10);
				TextArea ta2 = new TextArea();
				ta2.setPrefSize(170, 170);

				Button button = new Button("Back");
				button.setOnAction(s -> {
					stage.setScene(scene7);
				});

				Button button2 = new Button("Print");
				button2.setPrefSize(150, 10);
				button2.setStyle(
						" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
								+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
								+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8,"
								+ "#d8a0d8," + "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
								+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
								+ "-fx-font-weight: bold;");

				button2.setOnAction(c -> {
					Department f = new Department(ta1.getText());
					Department f2 = departments.find(f);
					ta2.setText(f2.students.print());
				});

				button.setPrefSize(150, 10);
				button.setStyle(
						" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
								+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
								+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8,"
								+ "#d8a0d8," + "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
								+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
								+ "-fx-font-weight: bold;");

				HBox h = new HBox();
				h.getChildren().addAll(ll2, ta1);
				h.setSpacing(10);
				h.setAlignment(Pos.CENTER);

				HBox h2 = new HBox();
				h2.getChildren().addAll(button, button2);
				h2.setSpacing(15);
				h2.setAlignment(Pos.CENTER);

				VBox v = new VBox();
				v.getChildren().addAll(h, ta2);
				v.setAlignment(Pos.CENTER);
				v.setSpacing(5);

				BorderPane p = new BorderPane();
				p.setTop(ll);
				p.setAlignment(ll, Pos.CENTER);
				p.setCenter(v);
				p.setBottom(h2);
				p.setAlignment(h2, Pos.CENTER);

				Scene scene8 = new Scene(p, 400, 250);
				stage.setScene(scene8);
				stage.show();

			});

			Button button2 = new Button("Insert Student");
			button2.setPrefSize(150, 10);
			button2.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			button2.setOnAction(s -> {
				Label l1 = new Label("Inserting Student");
				l1.setFont(new Font(30));

				Label l2 = new Label("Enter Department Name:");
				l2.setFont(new Font(15));

				Label l3 = new Label("Enter Student Name:");
				l3.setFont(new Font(15));

				Label l4 = new Label("Enter Student ID:");
				l4.setFont(new Font(15));

				Label l5 = new Label("Enter Student average:");
				l5.setFont(new Font(15));

				Label l6 = new Label("Enter Student Gender:");
				l6.setFont(new Font(15));

				TextArea ta1 = new TextArea(); // department
				ta1.setPrefSize(120, 10);

				TextArea ta2 = new TextArea(); // name
				ta2.setPrefSize(120, 10);

				TextArea ta3 = new TextArea(); // id
				ta3.setPrefSize(120, 10);

				TextArea ta4 = new TextArea(); // avg
				ta4.setPrefSize(120, 10);

				TextArea ta5 = new TextArea(); // gender
				ta5.setPrefSize(120, 10);

				Button sb1 = new Button("ADD");
				sb1.setPrefSize(150, 10);
				sb1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");

				sb1.setOnAction(v -> {
					Department f = new Department(ta1.getText());
					Department f2 = departments.find(f);
					f2.students.insert(new Student(ta2.getText(), Integer.parseInt(ta3.getText()),
							Double.parseDouble(ta4.getText()), ta5.getText()));
					ta1.clear();
					ta2.clear();
					ta3.clear();
					ta4.clear();
					ta5.clear();
				});

				Button sb2 = new Button("Back");
				sb2.setPrefSize(150, 10);
				sb2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				sb2.setOnAction(z -> {
					stage.setScene(scene7);
				});

				GridPane p = new GridPane();
				p.addColumn(0, l2, l3, l4, l5, l6);
				p.addColumn(1, ta1, ta2, ta3, ta4, ta5);
				p.setVgap(5);
				p.setHgap(5);
				p.setAlignment(Pos.CENTER);

				HBox h = new HBox();
				h.getChildren().addAll(sb1, sb2);
				h.setAlignment(Pos.CENTER);
				h.setSpacing(15);

				BorderPane pane = new BorderPane();
				pane.setTop(l1);
				pane.setAlignment(l1, Pos.CENTER);
				pane.setCenter(p);
				pane.setBottom(h);

				Scene scene9 = new Scene(pane, 360, 300);
				stage.setScene(scene9);
				stage.show();

			});

			Button button3 = new Button("Serch for Student");
			button3.setPrefSize(150, 10);
			button3.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			button3.setOnAction(c -> {
				Label l1 = new Label("Serching for a Student");
				l1.setFont(new Font(30));

				Label l2 = new Label("Enter Department Name:");
				l2.setFont(new Font(15));

				Label l3 = new Label("Enter Student Name:");
				l3.setFont(new Font(15));

				Label l4 = new Label(" Student ID:");
				l4.setFont(new Font(15));

				Label l5 = new Label(" Student average:");
				l5.setFont(new Font(15));

				Label l6 = new Label(" Student Gender:");
				l6.setFont(new Font(15));

				TextArea ta1 = new TextArea(); // department
				ta1.setPrefSize(120, 10);

				TextArea ta2 = new TextArea(); // name
				ta2.setPrefSize(120, 10);

				TextArea ta3 = new TextArea(); // id
				ta3.setPrefSize(120, 10);

				TextArea ta4 = new TextArea(); // avg
				ta4.setPrefSize(120, 10);

				TextArea ta5 = new TextArea(); // gender
				ta5.setPrefSize(120, 10);

				Button sb1 = new Button("Serch");
				sb1.setPrefSize(150, 10);
				sb1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");

				sb1.setOnAction(v -> {
					Department f = new Department(ta1.getText());
					Department f2 = departments.find(f);
					Student s = new Student(ta2.getText());
					Student s2 = f2.students.search(s);
					ta3.setText(String.valueOf(s2.getId()));
					ta4.setText(String.valueOf(s2.getAvg()));
					ta5.setText(s2.getGengder());
				});

				Button sb2 = new Button("Back");
				sb2.setPrefSize(150, 10);
				sb2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				sb2.setOnAction(z -> {
					stage.setScene(scene7);
				});

				GridPane p = new GridPane();
				p.addColumn(0, l2, l3, l4, l5, l6);
				p.addColumn(1, ta1, ta2, ta3, ta4, ta5);
				p.setVgap(5);
				p.setHgap(5);
				p.setAlignment(Pos.CENTER);

				HBox h = new HBox();
				h.getChildren().addAll(sb1, sb2);
				h.setAlignment(Pos.CENTER);
				h.setSpacing(15);

				BorderPane pane = new BorderPane();
				pane.setTop(l1);
				pane.setAlignment(l1, Pos.CENTER);
				pane.setCenter(p);
				pane.setBottom(h);

				Scene scene10 = new Scene(pane, 360, 300);
				stage.setScene(scene10);
				stage.show();
			});

			Button button4 = new Button("Delete Student");
			button4.setPrefSize(150, 10);
			button4.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");

			button4.setOnAction(x -> {
				Label l1 = new Label("Serching for a Student");
				l1.setFont(new Font(30));

				Label l2 = new Label("Enter Department Name:");
				l2.setFont(new Font(15));

				Label l3 = new Label("Enter Student Name:");
				l3.setFont(new Font(15));

				Label l4 = new Label(" Student ID:");
				l4.setFont(new Font(15));

				Label l5 = new Label(" Student average:");
				l5.setFont(new Font(15));

				Label l6 = new Label(" Student Gender:");
				l6.setFont(new Font(15));

				TextArea ta1 = new TextArea(); // department
				ta1.setPrefSize(120, 10);

				TextArea ta2 = new TextArea(); // name
				ta2.setPrefSize(120, 10);

				TextArea ta3 = new TextArea(); // id
				ta3.setPrefSize(120, 10);

				TextArea ta4 = new TextArea(); // avg
				ta4.setPrefSize(120, 10);

				TextArea ta5 = new TextArea(); // gender
				ta5.setPrefSize(120, 10);

				Button sb1 = new Button("Serch");
				sb1.setPrefSize(100, 10);
				sb1.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");

				sb1.setOnAction(v -> {
					Department f = new Department(ta1.getText());
					Department f2 = departments.find(f);
					Student s = new Student(ta2.getText());
					Student s2 = f2.students.search(s);
					ta3.setText(String.valueOf(s2.getId()));
					ta4.setText(String.valueOf(s2.getAvg()));
					ta5.setText(s2.getGengder());
				});

				Button sb3 = new Button("Delete");
				sb3.setPrefSize(100, 10);
				sb3.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");

				sb3.setOnAction(j -> {
					Department f = new Department(ta1.getText());
					Department f2 = departments.find(f);
					Student s = new Student(ta2.getText());
					Student s2 = f2.students.search(s);
					f2.students.delete(s2);
					ta1.clear();
					ta2.clear();
					ta3.clear();
					ta4.clear();
					ta5.clear();
				});

				Button sb2 = new Button("Back");
				sb2.setPrefSize(100, 10);
				sb2.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				sb2.setOnAction(z -> {
					stage.setScene(scene7);
				});

				GridPane p = new GridPane();
				p.addColumn(0, l2, l3, l4, l5, l6);
				p.addColumn(1, ta1, ta2, ta3, ta4, ta5);
				p.setVgap(5);
				p.setHgap(5);
				p.setAlignment(Pos.CENTER);

				HBox h = new HBox();
				h.getChildren().addAll(sb2,sb1,sb3);
				h.setAlignment(Pos.CENTER);
				h.setSpacing(15);

				BorderPane pane = new BorderPane();
				pane.setTop(l1);
				pane.setAlignment(l1, Pos.CENTER);
				pane.setCenter(p);
				pane.setBottom(h);

				Scene scene11 = new Scene(pane, 360, 300);
				stage.setScene(scene11);
				stage.show();
			});

			Button button5 = new Button("Save Hash Table to File");
			button5.setPrefSize(150, 10);
			button5.setStyle("-fx-background-color: #b197c3;\r\n" + "        -fx-background-radius:100;\r\n");
			
			button5.setOnAction(x->{
				Label lable=new Label("Saving");
				lable.setFont(new Font(30));
				
				Label lable2=new Label("Enter Department Name");
				lable2.setFont(new Font(15));
				
				TextArea t=new TextArea();
				t.setPrefSize(120, 10);
				
				Button back=new Button("Back");
				back.setPrefSize(120, 10);
				back.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				
				back.setOnAction(f->{
					stage.setScene(scene7);
				});
				
				Button save=new Button("Save");
				save.setPrefSize(120, 10);
				save.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
						+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
						+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
						+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
						+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
						+ "-fx-font-weight: bold;");
				
				save.setOnAction(r->{
					Department f = new Department(t.getText());
					Department f2 = departments.find(f);
					
					FileWriter out;
					try {
						out = new FileWriter(new File(t.getText()+"_sts.txt"),true);
						PrintWriter write=new PrintWriter(out);
						write.println(f2.students.print());
						write.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
				
				VBox v=new VBox();
				v.getChildren().addAll(lable2,t);
				v.setSpacing(5);
				v.setAlignment(Pos.CENTER);
				
				HBox h=new HBox();
				h.getChildren().addAll(back,save);
				h.setAlignment(Pos.CENTER);
				h.setSpacing(15);
				
				BorderPane p=new BorderPane();
				p.setTop(lable);
				p.setAlignment(lable2, Pos.CENTER);
				p.setCenter(v);
				p.setBottom(h);
				p.setAlignment(lable, Pos.CENTER);
				
				Scene scene12=new Scene(p,400,250);
				stage.setScene(scene12);
				stage.show();
				
				
			});

			Button button6 = new Button("Back");
			button6.setOnAction(s -> {
				stage.setScene(scene1);
			});
			button6.setPrefSize(120, 10);
			button6.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
					+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
					+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
					+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
					+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n"
					+ "-fx-font-weight: bold;");

			GridPane p = new GridPane();
			p.addColumn(0, button1, button2, button3);
			p.addColumn(1, button4, button5);
			p.setHgap(5);
			p.setVgap(5);
			p.setAlignment(Pos.CENTER);

			bp2.setTop(label);
			bp2.setAlignment(label, Pos.CENTER);
			bp2.setCenter(p);
			bp2.setAlignment(p, Pos.CENTER);
			bp2.setBottom(button6);
			bp2.setAlignment(button6, Pos.CENTER);

			stage.setScene(scene7);
			stage.show();

		});

		Button b3 = new Button("Exit");
		b3.setPrefSize(120, 20);
		b3.setStyle(" -fx-padding: 8 15 15 15;\r\n" + "-fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\r\r"
				+ "-fx-background-radius: 8;\r\n" + "-fx-background-color:"
				+ "linear-gradient(#d8a0d8, #a34313 0%,  #932693\r\n" + " 100%)," + "#d8a0d8," + "#d8a0d8,"
				+ "radial-gradient(center 50% 50%, radius 100%, #d8a0d8, #d8a0d8);\r\n"
				+ "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\r\n" + "-fx-font-weight: bold;");
		b3.setOnAction(e -> {
			stage.close();
		});

		VBox vb = new VBox();
		vb.getChildren().addAll(b1, b2, b3);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);

		bp.setAlignment(l, Pos.CENTER);
		bp.setTop(l);
		bp.setCenter(vb);
		stage.setScene(scene1);
		stage.setTitle("University");
		stage.show();
	}

	public void remove(String filepath, String removeTerm) {
		String tempFile = "temp.txt";
		File oldFile = new File(filepath);
		File newFile = new File(tempFile);
		String currentLine;
		String data[];

		try {
			FileWriter fw = new FileWriter(tempFile, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			while ((currentLine = br.readLine()) != null) {
				data = currentLine.split("/");
				if (!(data[0].equalsIgnoreCase(removeTerm))) {
					pw.println(currentLine);
				}
			}
			pw.flush();
			pw.close();
			fr.close();
			br.close();
			bw.close();
			fw.close();

			oldFile.delete();
			File dump = new File(filepath);
			newFile.renameTo(dump);
		} catch (Exception e) {
			System.out.println("Error!!");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
