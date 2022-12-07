<%@page import="library.model.Book"%>
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
    <link rel='stylesheet' type='text/css' media='screen' href='./css/register-style.css'>
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
                            <h3 class="m-0 font-weight-bold text-primary">Mượn sách</h3>
                        </div>
                        <div class="card-body">
			                <form action="register?action=submit" method="post">
				                <div class="row">
				                    <div class="col-6 col-sm-6">
				                    	<% Book book = (Book)request.getAttribute("book");%>
				                        <input type="hidden" name="book-id" id="book-id" value="<%=book.getId()%>">
				                        <div class="row">
				                            <div class="col-12">
				                                <div class="form-group">
				                                  <label><span>Tên sách : </span> <%=book.getName()%> </label>
				                                  <input type="hidden" name="book-name" id="book-name" value="<%=book.getName()%>">
				                                </div>
				                            </div>
				                        </div>
				                        <div class="row">
				                            <div class="col-6">
				                                <div class="form-group">
				                                  <label><span>Tác giả : </span><%=book.getAuthor().getName()%></label>
				                                  <input type="hidden" name="author" id="author" value="<%=book.getAuthor().getName()%>">
				                                </div>
				                            </div>
				                            <div class="col-6">
				                                <div class="form-group">
				                                  <label><span>Loại sách : </span><%=book.getCategory().getName()%></label>
				                                  <input type="hidden" name="category" id="category" value="<%=book.getCategory().getName()%>">
				                                </div>
				                            </div>
				                        </div>
				                        <div class="row">
				                            <div class="col-6">
				                                <div class="form-group">
				                                    <label><span>Số ngày mượn : </span></label>
				                                    <input type="text" name="number-day" id="number-day" class="form-control" placeholder="0">
				                                    <small id="number-day-help" class="text-muted small"></small>
				                                </div>
				                            </div>
				                        </div>
				                        <div class="row">
				                            <div class="col">
				                                <button type="submit" class="btn btn-primary">Mượn sách</button>
				                            </div>
				                        </div>
				                    </div>
				                </div>
			            	</form>
						</div>
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

</body>
</html>