<%@page import="library.model.Author"%>
<%@page import="library.model.Category"%>
<%@page import="library.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="./vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./css/cus-css.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="./vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="./css/style.css" rel="stylesheet">
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Heading -->
            <div class="sidebar-heading">
                <h6>Danh mục</h6>
            </div>

            <!-- Nav Item - register -->
            <li class="nav-item active">
                <a class="nav-link" href="bookList">
                   	<i class="fas fa-fw fa-table"></i>
                    <span>Kho sách</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="history">
                    <i class="fas fa-fw fa-history"></i>
                    <span>Lịch sử mượn</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <jsp:include page='./include/usermenu.jsp'/>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
					<div class="card shadow mb-4">

                        <div class="card-header py-3">
                            <h3 class="m-0 font-weight-bold text-primary">Thêm sách</h3>
                        </div>
                        <div class="card-body">
			                <form action="addBook?action=submit" method="post">
                                 <div class="row justify-content-center">
                                     <div class="col-11 col-sm-11">
                                         <div class="row">
                                             <div class="col-10">
                                                 <h1>Thông tin về sách</h1>
                                             </div>
                                         </div>
                                         <div class="row">
                                             <div class="col-8">
                                                 <div class="form-group">
                                                     <label>Tên sách :</label>
                                                     <input type="text" name="name-book" id="name-book" class="form-control form-control-user" placeholder="Tên sách">
                                                     <small id="name-book-help" class="text-muted small"></small>
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="row">
                                             <div class="col-4">
                                                 <div class="form-group">
                                                     <label for="category">Thể loại : </label>
                                                     <select class="form-control form-control-btn select-box" name="category" id="category">
                                                     <option value="">Choose...</option>
                                                     <%List<Category> categories = (List<Category>) request.getAttribute("categories"); 
                                                     	for(Category category : categories) {
                                                     %>
                                                     <option value="<%=category.getId() %>"><%=category.getName() %></option>
                                                     <%} %>
                                                 </select>
                                                     <small id="category-help" class="text-muted small"></small>
                                                 </div>
                                             </div>
                                             <div class="col-4">
                                                 <div class="form-group">
                                                     <label for="author">Tác giả : </label>
                                                     <select class="form-control form-control-btn select-box" name="author" id="author">
                                                     <option value="">Choose...</option>
                                                     <%List<Author> authors = (List<Author>) request.getAttribute("authors"); 
                                                     	for(Author author : authors) {
                                                     %>
                                                     <option value="<%=author.getId() %>"><%=author.getName() %></option>
                                                     <%} %>
                                                 </select>
                                                     <small id="author-help" class="text-muted small"></small>
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="row">
                                             <div class="col">
                                                 <button type="submit" class="btn btn-success form-control-btn" id="submit">Thêm sách</button>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                             </form>
						</div>
	                </div>
	                <!-- /.container-fluid -->

	            </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <jsp:include page='./include/footer.jsp'/>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <jsp:include page='./include/popup.jsp'>
    	<jsp:param name="message" value="Select 'Logout' below if you are ready to end your current session." />
    </jsp:include>

    <!-- Bootstrap core JavaScript-->
    <script src="./vendor/jquery/jquery.min.js"></script>
    <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="./vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="./js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="./vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="./vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="./js/demo/datatables-demo.js"></script>

</body>
</html>